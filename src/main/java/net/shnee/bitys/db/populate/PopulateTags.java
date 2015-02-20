package net.shnee.bitys.db.populate;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import net.shnee.bitys.model.Entity;
import net.shnee.bitys.model.Tag;

/**
 * Adds tags to the Tags table.
 */
public class PopulateTags {

    /** Parent tag for sports */
    private static final Tag SPORT = new Tag("Sport", "SPRT", null);

    private static final Set<Tag> MANIFEST = new HashSet<>(Arrays.asList(
                       PopulateTags.SPORT,
                       new Tag("Football",          "FB",   PopulateTags.SPORT),
                       new Tag("American Football", "AFB",  PopulateTags.SPORT),
                       new Tag("Baseball",          "BSB",  PopulateTags.SPORT),
                       new Tag("Basketball",        "BB",   PopulateTags.SPORT),
                       new Tag("Hockey",            "HCKY", PopulateTags.SPORT)
                                                                        ));

    /**
     * Populates the Sports table.
     * @return Returns the number of Tags added.
     */
    public static Integer populate() {
        return Entity.saveOrUpdateAll(MANIFEST);
    }

    /**
     * Delete all the current records and then populate the Sports table.
     * @return Returns the number of Sports added.
     */
    public static Integer deleteAllAndPopulate() {
        Integer numActions = Tag.deleteAll();
        return PopulateTags.populate();
    }

}
