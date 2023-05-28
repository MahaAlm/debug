package com.example.debug;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class details extends AppCompatActivity {
    DataBaseHelper dbTool;
    ImageView homeicon;
    ImageView logouticon;

    TextView name,rate,overview,model,cost,prod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        homeicon = (ImageView) findViewById(R.id.homeicons1);
        homeicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(details.this, HomeActivity.class));
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



        logouticon= (ImageView) findViewById(R.id.imageView33);
        logouticon.setOnClickListener(new View.OnClickListener(){
            @Override

            public void onClick(View view) {
                builder.show();

            }
        });


        int id=getIntent().getIntExtra("id",0);
        dbTool=new DataBaseHelper(this);
        Cursor cursor = dbTool.getOne(id);

        name=findViewById(R.id.det_Name);
        rate=findViewById(R.id.det_rate);
        overview=findViewById(R.id.det_overview);
        model=findViewById(R.id.det_model);
        cost=findViewById(R.id.det_usage);
        prod=findViewById(R.id.det_prod);


        if(cursor.moveToFirst()) {
            name.setText(cursor.getString(1));
            rate.setText(cursor.getString(2));
            model.setText(cursor.getString(3));
            overview.setText(cursor.getString(4));
            cost.setText(cursor.getString(5));
            prod.setText(cursor.getString(6));



        }



    }




}