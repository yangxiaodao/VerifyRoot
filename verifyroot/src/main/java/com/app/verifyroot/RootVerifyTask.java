package com.app.verifyroot;

import android.content.AsyncTaskLoader;
import android.content.Context;

public class RootVerifyTask extends AsyncTaskLoader<RootInfo> {

    public static final int ID = 5;

    private RootInfo mData;

    public RootVerifyTask(Context context) {
        super(context);
    }

    @Override
    public void deliverResult(RootInfo data) {
        if (isReset()) {
            if (mData != null)
                onReleaseResources(mData);
        }
        RootInfo oldData = mData;
        mData = data;
        if (isStarted()) {
            super.deliverResult(data);
        }
        if (oldData != null)
            onReleaseResources(oldData);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }

    @Override
    public void onCanceled(RootInfo data) {
        onReleaseResources(data);
    }

    @Override
    protected void onReset() {
        onStopLoading();

        if (mData != null) {
            onReleaseResources(mData);
            mData = null;
        }
    }

    protected void onReleaseResources(RootInfo data) {
    }

    @Override
    public RootInfo loadInBackground() {
        RootInfo resultRoot = new RootTest().test();
        return resultRoot;
    }
}
