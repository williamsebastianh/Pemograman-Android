package com.example.helloworld;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class Fragment1 extends Fragment {
    private static final String TAG = "Fragment1";
    public static String MY_FLAG = "MY_FLAG";
    private Button mulai;
    private Button cancel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_1, container,false);

       mulai= view.findViewById(R.id.mulai);
        cancel=view.findViewById(R.id.cancel);
    Log.i(TAG, "onCreateView: button: "+mulai);
        Log.i(TAG, "onCreateView: memulai service");
        serviceInit();
        return view;

    }

    private void onStartJobService() {
        Log.i(TAG, "Masuk ke onStartJobService");
        Log.i(TAG, "onStartJobService: Membuat componentName");
        ComponentName componentName = new ComponentName(requireActivity().getApplicationContext(), MyJobService.class);
        Log.i(TAG, "onStartJobService: membuat info");
        JobInfo info = new JobInfo.Builder(121018, componentName)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .setPersisted(true)
                .setPeriodic(15 * 60 * 1000)
                .build();
        Log.i(TAG, "onStartJobService: membuat scheduler");
        JobScheduler scheduler = (JobScheduler) requireContext().getSystemService(Context.JOB_SCHEDULER_SERVICE);
        int resultCode = scheduler.schedule(info);
        Log.i(TAG, "onStartJobService: pengecekan resultCode");
        if (resultCode == JobScheduler.RESULT_SUCCESS) {
            Log.i(TAG, "onStartJobService: job berhasil di buat");
        } else {
            Log.i(TAG, "onStartJobService: job scheduling failed");
        }
    }

    private void onStopJobService() {
        JobScheduler scheduler = (JobScheduler) requireContext().getSystemService(Context.JOB_SCHEDULER_SERVICE);
        scheduler.cancel(121018);
        Log.i(TAG, "onStopJobService: job di hentikan");
        Toast.makeText(requireContext().getApplicationContext()
                , "Service dihentikan", Toast.LENGTH_SHORT).show();
    }

    private void serviceInit() {
        Log.i(TAG, "serviceInit: masuk service init");
       mulai.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Log.i(TAG, "onClick:button mulai di tekan");
               onStartJobService();
           }
       });
        cancel.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: coba");

                onStopJobService();
            }
        } ));
    }
}