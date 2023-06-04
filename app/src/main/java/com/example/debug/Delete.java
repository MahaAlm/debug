package com.example.debug;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class Delete extends AppCompatActivity {

    private EditText It_user, It_Id;
    private Button del_but;

    ImageView homeicon;
    ImageView logouticon;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);


        String nam=getIntent().getStringExtra("name");



        homeicon = (ImageView) findViewById(R.id.homeicons11);
        homeicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String na=getIntent().getStringExtra("name");

                Intent intent=new Intent(Delete.this, HomeActivity.class);
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



        logouticon= (ImageView) findViewById(R.id.imageView23);
        logouticon.setOnClickListener(new View.OnClickListener(){
            @Override

            public void onClick(View view) {
                builder.show();

            }
        });


        DataBaseHelper DB=new DataBaseHelper(this);;
        It_Id= findViewById(R.id.Item_id);
        It_user= findViewById(R.id.Item_user);


        del_but=findViewById(R.id.sub_delete);



        del_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id= Integer.parseInt(It_Id.getText().toString());

                if (!DB.checkRented(id) && DB.checkUsernameDel(It_user.getText().toString(),id)){

                    DB.DeleteOne(Integer.parseInt(It_Id.getText().toString()));
                    Toast.makeText(Delete.this, "Deleted successfully", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(Delete.this, HomeActivity.class);
                    intent.putExtra("name",nam);
                    startActivity(intent);

                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Delete.this);
                    builder.setTitle("Warning");
                    builder.setMessage("Can't delete the tool, input is wrong or tool is rented");
                    builder.setNegativeButton("OK",null);

                    builder.show();



                }
            }
        });





    }
}