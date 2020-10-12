package com.example.helloworld;



import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = LoginActivity.class.getSimpleName();
    private EditText txtUsername;
    private EditText txtPassword;
    private Button btnlogin;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        btnlogin = findViewById(R.id.btnlogin);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                if(txtPassword.getText().toString().equals("admin") && txtUsername.getText().toString().equals("admin")){
                    Intent i = new Intent( LoginActivity.this, HomeActivity.class);
                    Toast.makeText(getApplicationContext(), "Selamat Datang "+ txtUsername.getText(),   Toast.LENGTH_LONG).show();
                    startActivity(i);
                }else{
                    Toast.makeText(getApplicationContext(),"Mohon Maaf Tidak Mengetahui "+ txtUsername.getText(), Toast.LENGTH_LONG).show();
                }}
        });
    }}