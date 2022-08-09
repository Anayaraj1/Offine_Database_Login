package com.example.mydb_example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {


    EditText edemail,edpassword;
    Button btnsignin;
    MyDatabase mydb;
    Cursor c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edemail = findViewById(R.id.edemail_login);
        edpassword = findViewById(R.id.edpassword_login);

        mydb = new MyDatabase(LoginActivity.this);


        btnsignin = findViewById(R.id.btn_signin);
        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c = mydb.LoginUser(edemail.getText().toString(),edpassword.getText().toString());
                if (c.getCount()>0)
                {
                    if (c.moveToFirst())
                    {
                        do {
                            String username =c.getString(1);
                            Intent i =new Intent(LoginActivity.this,MainScreenActivity.class);
                            i.putExtra("username",username);
                            startActivity(i);
                        }while (c.moveToNext());
                    }
                }
            }
        });
    }
}