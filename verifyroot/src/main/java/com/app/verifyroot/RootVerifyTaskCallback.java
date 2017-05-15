package com.app.verifyroot;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;
import android.util.Log;

public abstract class RootVerifyTaskCallback implements LoaderManager.LoaderCallbacks<RootInfo> {

    public Context mContext;

    public RootVerifyTaskCallback(Activity mContext) {
        this.mContext = mContext;
    }

    @Override
    public Loader<RootInfo> onCreateLoader(int id, Bundle args) {
        return new RootVerifyTask(mContext);
    }

    @Override
    public void onLoadFinished(Loader<RootInfo> loader, RootInfo data) {
        Log.d("TAG", data.getPrimaryInfo(mContext));
        onVerifyFinished(data.getRootAccess());
    }

    @Override
    public void onLoaderReset(Loader<RootInfo> loader) {

    }

    public abstract void onVerifyFinished(boolean access);
}
