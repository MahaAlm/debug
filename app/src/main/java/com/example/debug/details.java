package com.example.debug;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class details extends AppCompatActivity {
    DataBaseHelper dbTool;
    ImageView homeicon;
    ImageView logouticon;
    Button button,button1;


    TextView name,rate,overview,model,cost,prod;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);




        homeicon = (ImageView) findViewById(R.id.homeicons1);
        homeicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String na=getIntent().getStringExtra("name");
                Intent intent=new Intent(details.this, HomeActivity.class);
                intent.putExtra("name",na);
                startActivity(intent);
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
        String name1=getIntent().getStringExtra("name");
        dbTool=new DataBaseHelper(this);
        Cursor cursor = dbTool.getOne(id);


        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setTitle("Confirm");
        builder1.setMessage("Are you sure you want to Rent this tool and abide by the rules?");
        builder1.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dbTool.addToRent(id,name1);
            }
        });
        builder1.setNegativeButton("No",null);


        button=(Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!dbTool.checkRented(id) && !dbTool.getRdata2(name1,id)){
                    builder1.show();
                }else{
                    Toast.makeText(details.this, "The tool can't be rented", Toast.LENGTH_SHORT).show();
                }
            }
        });



        button1=(Button) findViewById(R.id.button2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dbTool.checkRented(id)){
                    if(dbTool.getRdata(name1,id)){
                        dbTool.deleteFromRent(id);
                        Toast.makeText(details.this, "You returned this tool successfully", Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(details.this, "You did not rent this tool", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(details.this, "The tool is not rented", Toast.LENGTH_SHORT).show();
                }
            }
        });






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