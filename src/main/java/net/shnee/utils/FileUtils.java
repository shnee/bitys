package net.shnee.utils;

import java.io.File;

/**
 * Utility class for the filesystem.
 */
public class FileUtils {

    /**
     * @param file Full path of the file being checked.
     * @return Returns true if the file exists. Returns false if the path points
     *         to a directory or the file does not exists.
     */
    static public boolean fileExists(final String file) {
        File f = new File(file);
        return f.exists() && !f.isDirectory();
    }

}
