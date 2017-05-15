package com.app.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.app.verifyroot.RootUtils;
import com.app.verifyroot.RootVerifyTask;
import com.app.verifyroot.RootVerifyTaskCallback;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void checkRoot(View view) {
        getLoaderManager().restartLoader(RootVerifyTask.ID, null, new RootVerifyTaskCallback(this) {
            @Override
            public void onVerifyFinished(boolean access) {
                Toast.makeText(
                        getApplicationContext(),
                        "手机是否Root ： " + RootUtils.checkRootCommand() + "\n\n" +
                        "APP是否有Root权限 : " + access,
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}
