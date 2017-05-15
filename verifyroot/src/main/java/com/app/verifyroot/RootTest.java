package com.app.verifyroot;

import java.io.File;

public class RootTest {

    public RootInfo test() {
        RootInfo result = new RootInfo();
        Cmd cmd = new Cmd();
        cmd.setRaw(result.getRaw());
        cmd.setRuntimeExec("su");
        cmd.addCommand("echo -RVEOF-");
        cmd.addCommand("id");
        cmd.execute();
        result.mExitCode = cmd.getExitCode();
        if (cmd.getExitCode() == Cmd.OK || cmd.getExitCode() == Cmd.OUT_OF_RANGE) {
            for (String line : cmd.getOutput()) {
                if (line.contains("uid=0")) {
                    result.mGotRoot = true;
                }
            }
        } else if (cmd.getExitCode() == Cmd.COMMAND_NOT_FOUND) {
            result.mBinaryIssue = true;
            cmd.clearCommands();
            cmd.addCommand("echo -RVEOF-");
            File testFolder = new File("/cache/rootvalidator.tmp");
            cmd.addCommand("echo test > " + testFolder.getAbsolutePath());
            cmd.execute();
            if (cmd.getExitCode() == Cmd.OK) {
                result.mGotRoot = true;
            }
        } else if (cmd.getExitCode() == Cmd.PROBLEM) {
            // Unknown error, check if su was denied by su app?
        }
        return result;
    }
}
