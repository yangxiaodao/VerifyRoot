package com.app.verifyroot;

import java.io.IOException;
import java.io.OutputStreamWriter;

public class RootUtils {

    public static boolean checkRootCommand() {
        Process exec;
        try {
            exec = Runtime.getRuntime().exec(new String[]{"su", "-c"});
            final OutputStreamWriter out = new OutputStreamWriter(exec.getOutputStream());
            out.write("exit");
            out.flush();
            return true; // returns zero when the command is executed successfully
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; //returns one when the command execution fails
    }
}
