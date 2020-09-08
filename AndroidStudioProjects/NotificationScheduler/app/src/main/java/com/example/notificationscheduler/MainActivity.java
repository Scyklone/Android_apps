package com.example.notificationscheduler;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int JOB_ID = 101;
    private JobScheduler jobScheduler;
    private JobInfo jobInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ComponentName componentName = new ComponentName(this,MJobScheduler.class);
        JobInfo.Builder builder = new JobInfo.Builder(JOB_ID, componentName);

        builder.setPeriodic(5000);
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
        builder.setPersisted(true);

        jobInfo = builder.build();
        jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);

    }



    private static final String TAG = "MainActivity";


    public void scheduleJob(View v) {
        jobScheduler.schedule(jobInfo);
        Toast.makeText(this,"Job Scheduled...",Toast.LENGTH_SHORT).show();
        Log.d(TAG,"Job Scheduled...");

    }

    public void cancelJob(View v) {
        jobScheduler.cancel(JOB_ID);
        Toast.makeText(this,"Job Cancelled...",Toast.LENGTH_SHORT).show();
        Log.d(TAG,"Job Cancelled...");
    }
}
