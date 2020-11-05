package com.example.helloworld;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import org.jetbrains.annotations.Nullable;

public class Mainactivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vieww);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportFragmentManager().beginTransaction().replace(R.id.maincontainer, new Fragment2()).commit();
    }
}