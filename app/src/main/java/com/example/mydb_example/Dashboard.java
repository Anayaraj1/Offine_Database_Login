package com.example.mydb_example;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {

    RecyclerView rev;


    MyDatabase myDatabase;
    Cursor c;
    ArrayList<Model>  al = new ArrayList<>();

    FloatingActionButton fab,fabadd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        rev = findViewById(R.id.rev);
        fab = findViewById(R.id.fab_add);
        fabadd=findViewById(R.id.fab_login);

        fabadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(Dashboard.this,LoginActivity.class);
                finish();
                startActivity(i);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Dashboard.this,MainActivity.class);
                finish();
                startActivity(i);
            }
        });

        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(Dashboard.this);
        rev.setLayoutManager(layoutManager);

        myDatabase = new MyDatabase(Dashboard.this);


        c = myDatabase.getallData();

        if (c.getCount()>0)
        {
            if (c.moveToFirst())
            {
                do {
                    Model model = new Model(c.getInt(0), c.getString(1), c.getString(2), c.getString(3));
                    al.add(model);

                    MyAdapter my =new MyAdapter(al,Dashboard.this);
                    rev.setAdapter(my);

                }while (c.moveToNext());
            }
        }
            }

}