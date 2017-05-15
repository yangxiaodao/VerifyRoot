package com.app.verifyroot;

import android.content.Context;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RootInfo implements Serializable {

    private final List<String> mRaw = new ArrayList<>();

    boolean mGotRoot = false;
    boolean mBinaryIssue = false;
    int mExitCode = 99;

    public String getPrimaryInfo(Context context) {
        if (mGotRoot) {
            return "Root available!";
        } else if (!mBinaryIssue) {
            return "Root denied?";
        } else {
            return "Root unavailable";
        }
    }

    public boolean getRootAccess() {
        if (mGotRoot) {
            return true;
        } else if (!mBinaryIssue) {
            return false;
        } else {
            return true;
        }
    }

    public List<String> getRaw() {
        return mRaw;
    }
}
