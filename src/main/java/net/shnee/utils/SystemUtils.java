package net.shnee.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
public class SystemUtils {

    static public String runCommand(final String command) throws Exception {
        try {
            List<String> list = SystemUtils.splitString(command);
            ProcessBuilder procBuilder = new ProcessBuilder(list);
            Process proc = procBuilder.start();

            InputStream is = proc.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            /* 1024 is an arbitrary number, default value is 16, so hopefully by
             * giving it a larger number this will speed things up. */
            StringBuilder stringBuilder = new StringBuilder(1024);
            String line;
            while ((line = br.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }

            // Check if process completed successfully.
            int exitValue = proc.waitFor();
            if(exitValue != 0) { /* 0 means the process exited successfully */
                Logger.getLogger(SystemUtils.class.getName()).log(Level.SEVERE,
                        "Command did not exit successfully.");
                throw new Exception("Command did not exit successfully.");
            }

            return stringBuilder.toString();

        } catch(IOException ex) {
            Logger.getLogger(SystemUtils.class.getName()).log(Level.SEVERE,
                    "Error trying to run command: {0}", command);
            throw new Exception("Error trying to run command: " + command, ex);
        } catch(InterruptedException ex) {
            Logger.getLogger(SystemUtils.class.getName()).log(Level.SEVERE,
                    "Interrupted command: {0}", command);
            throw new Exception("Interrupted command: " + command, ex);
        }
    }

    /**
     * Will split the given string around ' '. It will keep substrings in quotes
     * in tact.
     * @param string The string to be split into substrings.
     * @return A list of the substring tokens.
     */
    static private List<String> splitString(final String string) {
        List<String> list = new ArrayList<>();
        Matcher m = Pattern.compile("([^\"]\\S*|\".+?\")\\s*").matcher(string);
        while (m.find()) {
            list.add(m.group(1).replace("\"", ""));
        }
        return list;
    }
}
