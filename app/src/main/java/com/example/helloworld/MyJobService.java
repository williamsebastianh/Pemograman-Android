package com.example.helloworld;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

public class MyJobService extends JobService {
    private static final String TAG=MyJobService.class.getSimpleName();
    private boolean jobcancel=false;
    Context context;
    @Override
    public boolean onStartJob(JobParameters params) {
        Log.i(TAG, "onstartjob");
        context=getApplicationContext();
        doBackground(params);
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.i(TAG,"onstopjob: cancel");
        jobcancel=true;
        return true;
    }
    private void doBackground (final JobParameters parameters){
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<10; i++){
                    Log.i(TAG,"run:"+i);
                    Toast.makeText(getApplicationContext(), "mencoba "+ i,Toast.LENGTH_SHORT).show();
                    final int finali = i;
                    Handler mHandler = new Handler(getMainLooper());
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            Log.i(TAG,"Handler Run: "+finali);
                        }
                    });
                    if(jobcancel){
                        return ;
                    }try{
                        Thread.sleep(3000);
                    }catch (InterruptedException e){
                        Log.e(TAG,"InterruptedException: ",e.getCause());
                    }
                }
             Log.i(TAG,"run: SELESAI");
            }
        }).start();
    }
}
