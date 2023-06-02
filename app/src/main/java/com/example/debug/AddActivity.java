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
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    ImageView home;
    ImageView logout;
    EditText name, model,prodY ,overview, Amount, username;
    Button SubmitSave;
    DataBaseHelper dataBaseHelper;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        home = (ImageView) findViewById(R.id.homeicons);
        home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddActivity.this, HomeActivity.class));
            }
        });


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Log out");
        builder.setMessage("Are you sure you want to log out?");
        builder.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Cancel",null);



        logout = (ImageView) findViewById(R.id.imageView3);
        logout.setOnClickListener(new View.OnClickListener(){
            @Override

            public void onClick(View view) {
                builder.show();

            }
        });



        name = findViewById(R.id.name);
        model = findViewById(R.id.model);
        Amount = findViewById(R.id.Amount);
        prodY=findViewById(R.id.prodY);
        username=findViewById(R.id.usernameEntry);
        overview = findViewById(R.id.overview);
        SubmitSave = findViewById(R.id.btnSubmit);

        dataBaseHelper = new DataBaseHelper(AddActivity.this);

        SubmitSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Name = name.getText().toString();
                String Model = model.getText().toString();
                String over = overview.getText().toString();
                String amount = Amount.getText().toString();
                String prod = prodY.getText().toString();
                String usernam= username.getText().toString();


                if (Name.isEmpty()) {
                    Toast.makeText(AddActivity.this, "Please fill the name field", Toast.LENGTH_SHORT).show();
                } else if (Model.isEmpty()) {
                    Toast.makeText(AddActivity.this, "Please fill the model field", Toast.LENGTH_SHORT).show();
                } else if (over.isEmpty()) {
                    Toast.makeText(AddActivity.this, "Please fill the overview field", Toast.LENGTH_SHORT).show();
                } else if (amount.isEmpty()) {
                    Toast.makeText(AddActivity.this, "Please fill the rent amount field", Toast.LENGTH_SHORT).show();
                } else if (prod.isEmpty()) {
                    Toast.makeText(AddActivity.this, "Please fill the production year field", Toast.LENGTH_SHORT).show();
                }else if (usernam.isEmpty()) {
                    Toast.makeText(AddActivity.this, "Please fill the username field", Toast.LENGTH_SHORT).show();
                }else if (!dataBaseHelper.checkUsername(usernam)) {
                    Toast.makeText(AddActivity.this, "Please Enter existing username", Toast.LENGTH_SHORT).show();
                }
                else {
                    // create model
                    toolModel toolMod;
                    try {
                        toolMod = new toolModel(-1, 0,Name, Model, over, Integer.parseInt(amount), prod,0 ,usernam);
                        DataBaseHelper dataBaseHelper = new DataBaseHelper(AddActivity.this);
                        boolean b = dataBaseHelper.addOne(toolMod, usernam);
                        if(b==true){
                            AlertDialog.Builder b1=  new AlertDialog.Builder(AddActivity.this);
                            b1.setTitle("Added successfully");
                            b1.setMessage("Your Tool is ready to rent!");
                            b1.setPositiveButton("Ok", new DialogInterface.OnClickListener(){
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                    startActivity(intent);
                                }
                            });
                            b1.show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(AddActivity.this, "Enter Valid input", Toast.LENGTH_SHORT).show();
                    }
                }
            }


        });






    }




}