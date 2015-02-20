package net.shnee.bitys.db.populate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.shnee.bitys.model.Entity;
import net.shnee.bitys.model.Tag;
import net.shnee.bitys.model.Team;
import org.apache.commons.lang3.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * @author shnee
 */
public class EntityPopulator {

    static final Integer TAG_ID     = 0;
    static final Integer TAG_NAME   = 1;
    static final Integer TAG_ABBREV = 2;
    static final Integer TAG_PARENT = 3;
    static final Integer TAG_FIELDS = 4;

    static final Integer TEAM_NAME   = 0;
    static final Integer TEAM_ABBREV = 1;
    static final Integer TEAM_TAG    = 2;
    static final Integer TEAM_FIELDS = 3;

    /** This maps the given id's from the text file to a Tag that has already
     *  been saved in the database. This is used to keep references in tact
     *  after a Tag has been save. This is needed because we cannot control the
     *  id of a saved Tag. */
    private static final Map<Integer,Tag> loadedTags = new HashMap<>();

    private static final Logger logger = LoggerFactory.getLogger(
                                                                EntityPopulator.
                                                                class.
                                                                getSimpleName()
                                                                );

    /**
     * TODO
     * @param <T>
     * @param type
     * @param filename
     * @return
     */
    public static <T> Integer populateFromFile(Class<T> type, String filename) {

        LinkedHashSet<String[]> csvSet = parseCSV(filename);
        Integer rv = 0;

        if(type == Tag.class) {
            for(String[] csvValues : csvSet) {
                Tag newTag = createTagFromStringArray(csvValues);
                if(newTag != null) {
                    if(Tag.saveOrUpdate(newTag) != null) {
                        rv++;
                    }
                    loadedTags.put(new Integer(csvValues[TAG_ID]), newTag);
                }
            }
        } else if(type == Team.class) {
            Set<Team> unsavedTeams = new LinkedHashSet<>();
            for(String[] csvValues : csvSet) {
                Team newTeam = createTeamFromStringArray(csvValues);
                if(newTeam != null) {
                    unsavedTeams.add(newTeam);
                }
            }
            rv = Team.saveOrUpdateAll(unsavedTeams);
        } else {
            throw new UnsupportedOperationException("Type: " +
                                                    type.getSimpleName() +
                                                    ", is not supported.");
        }

        return rv;
    }

    /**
     * Takes an array of Strings and creates a new instance of Tag.
     * @param csvValues Array of Strings that holds the data for a new Tag. The
     *                  assumed order of the Strings is id, name, abbrev,
     *                  parentId.
     * @return Returns a newly created Tag from the values in csvValues. Returns
     *         null if it was unable to create a Tag from csvValues.
     */
    public static Tag createTagFromStringArray(String[] csvValues) {
        Tag newTag = null;

        /** First things first, lets check to see if there are the correct
         *  number of elements in csvValues. */
        if(csvValues.length == TAG_FIELDS) {
            /* Check if there's a reference to a parent Tag. If so we need to lookup
             * the id of the parent. */
            if(csvValues[TAG_PARENT].trim().equals("NULL")) {
                newTag = new Tag(csvValues[TAG_NAME].trim(),
                                 csvValues[TAG_ABBREV].trim(),
                                 null);
            } else {
                try {
                    newTag = new Tag(csvValues[TAG_NAME].trim(),
                                     csvValues[TAG_ABBREV].trim(),
                                     loadedTags.get(new Integer(
                                                          csvValues[TAG_PARENT].
                                                          trim())));
                } catch(NumberFormatException e) {
                    EntityPopulator.logger.error("Error creating Tag from " +
                            "String array: " + Arrays.toString(csvValues) +
                            " , unable to convert " + csvValues[TAG_PARENT] +
                            " to an Integer, ex: " + e.getMessage());
                }
            }
        } else {
            EntityPopulator.logger.debug("Unable to create Tag from values: " +
                                         Arrays.toString(csvValues));
        }

        return newTag;
    }

    /**
     * Takes an array of Strings and creates a new instance of Team. If the
     * given Tag is not found in the database, Team is created with no Tags.
     * @param csvValues Array of Strings that holds the data for a new Team. The
     *                  assumed order of the Strings is name, abbrev, tagName.
     * @return Returns a newly created Team from the values in csvValues.
     *         Returns null if it was unable to create a Team from csvValues.
     */
    public static Team createTeamFromStringArray(String[] csvValues) {
        Team newTeam = null;

        /** First things first, lets check to see if there are the correct
         *  number of elements in csvValues. */
        if(csvValues.length == TEAM_FIELDS) {
            newTeam = new Team(csvValues[TEAM_NAME].trim(),
                               csvValues[TEAM_ABBREV].trim());
            if(!csvValues[TEAM_TAG].trim().equals("NULL")) {
                List<Tag> tags = Tag.getByName(csvValues[TEAM_TAG]);
                if(tags.size() == 1) {
                    newTeam.addTag(tags.get(0));
                } else if(tags.isEmpty()) {
                    EntityPopulator.logger.error("There are no tags with " +
                            "name: " + csvValues[TEAM_TAG] + ", unable to add" +
                            " to Team: " + newTeam);
                } else if(tags.size() > 1) {
                    EntityPopulator.logger.error("There are multiple tags " +
                            "with name: " + csvValues[TEAM_TAG] + ", unable " +
                            "to add to Team: " + newTeam);
                }
            }
        } else {
            EntityPopulator.logger.debug("Unable to create Team from values: " +
                                         Arrays.toString(csvValues));
        }

        return newTeam;
    }

    /**
     * TODO
     * @param filename
     * @return
     */
    private static LinkedHashSet<String[]> parseCSV(String filename) {
        LinkedHashSet<String[]> csvSet = new LinkedHashSet<>();
        BufferedReader br = null;
        String line = "";

        try {
            br = new BufferedReader(new FileReader(filename));
            while ((line = br.readLine()) != null) {
                String[] csvValues = line.split(",");

                csvSet.add(csvValues);
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return csvSet;
    }
}
