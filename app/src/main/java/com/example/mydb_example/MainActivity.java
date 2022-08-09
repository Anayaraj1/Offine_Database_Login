package com.example.mydb_example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText Username,Email,Password,confirmPassword;
    Button btnsubmit;

    MyDatabase mdb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Username = findViewById(R.id.ed_userrname);
        Email = findViewById(R.id.ed_email);
        Password = findViewById(R.id.ed_password);
        confirmPassword = findViewById(R.id.ed_cnf_password);
        btnsubmit = findViewById(R.id.btn_submit);

        mdb = new MyDatabase(MainActivity.this);

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Password.getText().toString().equals(confirmPassword.getText().toString()))
                {
                    long id = mdb.insertData(Username.getText().toString(),Email.getText().toString(),Password.getText().toString());
                    Log.d("mydata","id= "+id);
                    Toast.makeText(MainActivity.this, "succesfully data insert", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "password and confirm password must be same", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}