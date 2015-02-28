package com.pgyer.pgyersdk.test;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.pgyersdk.crash.PgyCrashManager;
import com.pgyersdk.feedback.PgyFeedbackShakeManager;
import com.pgyersdk.update.PgyUpdateManager;
import com.pgyersdk.update.UpdateManagerListener;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init()
    {
        Button btnCheckUpdate = (Button) findViewById(R.id.btnCheckUpdate);
        btnCheckUpdate.setOnClickListener(this);
        Button btnCrashReport = (Button) findViewById(R.id.btnCrashReport);
        btnCrashReport.setOnClickListener(this);
        Button btnOpenFeedback = (Button) findViewById(R.id.btnOpenFeedback);
        btnOpenFeedback.setOnClickListener(this);
        Button btnCloseFeedback = (Button) findViewById(R.id.btnCloseFeedback);
        btnCloseFeedback.setOnClickListener(this);
    }

    private void testCrashReport()
    {
        throw new RuntimeException("这是个测试bug!");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCheckUpdate:
                PgyUpdateManager.register(MainActivity.this, Constants.APPID, new UpdateManagerListener() {
                    @Override
                    public void onUpdateAvailable() {
                        // TODO Auto-generated method stub
                        Toast.makeText(MainActivity.this, "有新的版本", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNoUpdateAvailable() {
                        // TODO Auto-generated method stub
                        Toast.makeText(MainActivity.this, "已经是最新的版本", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.btnCrashReport:
                PgyCrashManager.register(MainActivity.this, Constants.APPID);
                testCrashReport();
                break;
            case R.id.btnOpenFeedback:
                PgyFeedbackShakeManager.register(MainActivity.this, Constants.APPID);
                break;
            case R.id.btnCloseFeedback:
                PgyFeedbackShakeManager.unregister();
                break;

            default:
                break;
        }
    }
}
