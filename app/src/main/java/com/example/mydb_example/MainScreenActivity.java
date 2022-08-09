package com.example.mydb_example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainScreenActivity extends AppCompatActivity {

    TextView tv;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        tv = findViewById(R.id.tv_login_username);
        username = getIntent().getStringExtra("username");
        tv.setText(username);
    }
}