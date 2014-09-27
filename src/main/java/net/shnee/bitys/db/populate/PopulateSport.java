package net.shnee.bitys.db.populate;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import net.shnee.bitys.model.Entity;
import net.shnee.bitys.model.Sport;

/**
 * Adds sports to the Sports table.
 */
public class PopulateSport {

    private static final Set<Sport> MANIFEST = new HashSet<>(Arrays.asList(
                                                 new Sport("Football"),
                                                 new Sport("American Football"),
                                                 new Sport("Baseball"),
                                                 new Sport("Basketball"),
                                                 new Sport("Hockey")
                                                                           ));

    /**
     * Populates the Sports table.
     * @return Returns the number of Sports added.
     */
    public static Integer populate() {
        return Entity.saveOrUpdateAll(MANIFEST);
    }

    /**
     * Delete all the current records and then populate the Sports table.
     * @return Returns the number of Sports added.
     */
    public static Integer deleteAllAndPopulate() {
        Integer numActions = Sport.removeAll();
        return PopulateSport.populate();
    }

}
