package net.shnee.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public class SystemUtils {

    static public String runCommand(final String command) throws Exception {
        try {
            ProcessBuilder procBuilder = new ProcessBuilder(SystemUtils.
                                                            splitString(
                                                                    command));
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

    static private List<String> splitString(final String string) {
        return Arrays.asList(string.split(string));
    }

}
