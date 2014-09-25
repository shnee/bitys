package net.shnee.bitys.db.populate;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import net.shnee.bitys.model.Sport;

/**
 * Adds sports to the Sports table.
 */
public class PopulateSport {
    
    static private Set<String> MANIFEST = new HashSet<>(Arrays.asList(
                                                            "Football",
                                                            "American Football",
                                                            "Baseball",
                                                            "Basketball",
                                                            "Hockey"
                                                                            ));

    public static Integer populate() {
        return 0;
    }
    
    private static Set<Sport> getSportsToAdd() {
        Set<Sport> saved = new HashSet<>(Sport.getAll());
        //Set<Sport> toAdd = new HashSet<>
        //List<Sport> toAdd = new
        return new HashSet<>();
    }

}
