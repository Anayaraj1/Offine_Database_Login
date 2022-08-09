package com.example.mydb_example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText Username,Email,Password,confirmPassword,ed_id;
    Button btnupdate;
    MyDatabase mydb;
    String id;
    Cursor c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        ed_id = findViewById(R.id.ed_id);
        Username = findViewById(R.id.ed_u_userrname);
        Email = findViewById(R.id.ed_u_email);
        Password = findViewById(R.id.ed_u_password);
        btnupdate = findViewById(R.id.btn_update);

        mydb = new MyDatabase(UpdateActivity.this);
        id=getIntent().getStringExtra("id");
        ed_id.setText(id);

        c=mydb.getSpecificData(id);

        if (c.getCount() > 0)
        {
            do{
                Username.setText(c.getString(1));
                Email.setText(c.getString(2));
                Password.setText(c.getString(3));
            }
            while (c.moveToNext());
        }

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mydb.updateData(Username.getText().toString(),Email.getText().toString(),Password.getText().toString(),id);
                Toast.makeText(UpdateActivity.this, "Successfully updated", Toast.LENGTH_SHORT).show();

                Intent  i =new Intent(UpdateActivity.this,Dashboard.class);
                finish();
                startActivity(i);
            }
        });
    }
}