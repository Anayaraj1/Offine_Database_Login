package com.example.mydb_example;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.Myclass>
{
    ArrayList<Model>al;
    MyDatabase mydb;
    Context context;

    public MyAdapter(ArrayList<Model> al, Context context) {
        this.al = al;
        this.context = context;
    }

    @NonNull
    @Override
    public Myclass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.myrow,parent,false);

        return new Myclass(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Myclass holder, int position) {

        Model m1=al.get(position);
        Log.d("mydata",""+m1.getId());
        holder.tvid.setText(""+m1.getId());
        holder.tvusername.setText(m1.getUsername());
        holder.tvemail.setText(m1.getEmail());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        holder.btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog d =new Dialog(context);
                mydb = new MyDatabase(context);

                d.setContentView(R.layout.mydialog);
                TextView tv = d.findViewById(R.id.tv_delete_name);
                Button btnyes=d.findViewById(R.id.btn_yes);
                Button btnno=d.findViewById(R.id.btn_no);
                tv.setText(m1.getUsername());


                btnyes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        mydb.deleteSpecificData(""+m1.getId());
                        Intent i =new Intent(context,Dashboard.class);
                        ((Activity)context).finish();
                        context.startActivity(i);
                    }
                });

                btnno.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        d.dismiss();
                    }
                });

                d.show();
            }
        });
        holder.btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(context,UpdateActivity.class);
                i.putExtra("id",""+m1.getId());
                context.startActivity(i);
            }
        });
    }



    @Override
    public int getItemCount() {
        return al.size();
    }
    class Myclass extends RecyclerView.ViewHolder
    {

        TextView tvid,tvusername,tvemail;
        Button btnedit,btndelete;

        public Myclass(@NonNull View itemView) {
            super(itemView);
            tvid=itemView.findViewById(R.id.tvid);
            tvusername = itemView.findViewById(R.id.tv_username);
            tvemail=itemView.findViewById(R.id.tv_email);
            btnedit=itemView.findViewById(R.id.btn_edit);
            btndelete=itemView.findViewById(R.id.btn_delete);

        }
    }


}
