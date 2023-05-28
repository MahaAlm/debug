package com.example.debug;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ToolActivity extends AppCompatActivity implements SelectListener{

    ImageView homeicon;

    ImageView btnDelete;
    ImageView logouticon;
    RecyclerView recyclerView;
    ArrayList<String> name, rate,id;

    com.example.debug.DataBaseHelper DB;
    com.example.debug.MyAdapter adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool);




        btnDelete = (ImageView) findViewById(R.id.delete);

        homeicon = (ImageView) findViewById(R.id.homeicons);
        homeicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ToolActivity.this, HomeActivity.class));
            }
        });


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Log out");
        builder.setMessage("Are you sure you want to log out?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("No",null);



        logouticon= (ImageView) findViewById(R.id.imageView3);
        logouticon.setOnClickListener(new View.OnClickListener(){
            @Override

            public void onClick(View view) {
                builder.show();

            }
        });



        btnDelete= findViewById(R.id.delete);
btnDelete.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getApplicationContext(), Delete.class);
        startActivity(intent);
    }
});

        DB = new DataBaseHelper(this);
        name = new ArrayList<>();
        rate = new ArrayList<>();

        id=new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerview);
        adapter = new MyAdapter(this, name, rate,id,this);
        recyclerView.setAdapter(adapter);
        GridLayoutManager gridLayoutManager= new GridLayoutManager(this,2,LinearLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(gridLayoutManager);
        displaydata();
}


    private void displaydata() {
        Cursor cursor = DB.getdata();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No tools for rent", Toast.LENGTH_SHORT).show();
            return;
        } else {
            while (cursor.moveToNext()) {
                    id.add(cursor.getString(0));
                    name.add(cursor.getString(1));
                    rate.add("Rate: "+cursor.getString(2));
                }

            }
        }
    @Override
    public void onItemClicked(int model) {
        toolModel selected_tool;
        Intent intent=new Intent(ToolActivity.this, details.class);
        intent.putExtra("id",model);
        startActivity(intent);
    }
}
