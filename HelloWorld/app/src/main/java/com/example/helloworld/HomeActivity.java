package com.example.helloworld;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.se.omapi.Channel;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.NotificationBuilderWithBuilderAccessor;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import static android.app.NotificationChannel.DEFAULT_CHANNEL_ID;

public class HomeActivity extends AppCompatActivity {

    private Switch swi;
    private WifiManager wm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TabLayout tabLayout = findViewById(R.id.tablayout);
        TabItem tabSatu = findViewById(R.id.tabSatu);
        TabItem tabDua = findViewById(R.id.tabDua);
        final ViewPager viewPager = findViewById(R.id.viewPager);

        PagerAdapter pageAdapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        swi = findViewById(R.id.wifiswi);
        BroadcastRec();
    }

    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION);
        registerReceiver(wifiStateReceiver, intentFilter);
    }

    protected void onStop() {
        super.onStop();
        unregisterReceiver(wifiStateReceiver);
    }


    private BroadcastReceiver wifiStateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int wifiStateExtra = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, wm.WIFI_STATE_UNKNOWN);

            switch (wifiStateExtra) {
                case WifiManager.WIFI_STATE_ENABLED:

                    swi.setChecked(true);
                    swi.setText("WIFI ON");
                    break;
                case WifiManager.WIFI_STATE_DISABLED:
                    swi.setChecked(false);
                    swi.setText("WIFI OFF");

                    break;

            }


            if (swi.getText().toString().equals("WIFI ON")) {
                String CHANNEL_ID = "MY_NOTIF";
                NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, "MY channel", NotificationManager.IMPORTANCE_HIGH);
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.createNotificationChannel(mChannel);
                Notification notification = new NotificationCompat.Builder(HomeActivity.this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.iconwifi)
                        .setContentTitle("STATUS WIFI")
                        .setContentText("WIFI MENYALA")
                        .build();
                int notificationID = 0;
                notificationManager.notify(notificationID, notification);
            } else if (swi.getText().toString().equals("WIFI OFF")) {
                String CHANNEL_ID = "MY_NOTIF";
                NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, "MY channel", NotificationManager.IMPORTANCE_HIGH);
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.createNotificationChannel(mChannel);
                Notification notification = new NotificationCompat.Builder(HomeActivity.this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.iconwifi)
                        .setContentTitle("STATUS WIFI")
                        .setContentText("WIFI MATI")
                        .build();
                int notificationID = 0;
                notificationManager.notify(notificationID, notification);

            }
        }
    };

    private void BroadcastRec() {
        final WifiManager wifiManager = (WifiManager)
                getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        swi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && !wifiManager.isWifiEnabled()) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {

                        Intent panelIntent = new Intent(Settings.Panel.ACTION_WIFI);
                        HomeActivity.this.startActivityForResult(panelIntent, 1);
                    } else {
                        wifiManager.setWifiEnabled(true);
                    }
                } else if (!isChecked && wifiManager.isWifiEnabled()) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        Intent panelIntent = new Intent(Settings.Panel.ACTION_WIFI);
                        HomeActivity.this.startActivityForResult(panelIntent, 1);
                    } else {

                        wifiManager.setWifiEnabled(false);
                    }
                }
            }
        });
    }
}
