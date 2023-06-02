package com.example.debug;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Delete extends AppCompatActivity {

    private EditText It_user, It_Id;
    private Button del_but;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);


        DataBaseHelper DB=new DataBaseHelper(this);;
        It_Id= findViewById(R.id.Item_id);
        It_user= findViewById(R.id.Item_user);


        del_but=findViewById(R.id.sub_delete);

        del_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DB.checkUsernameDel(It_user.getText().toString(),Integer.parseInt(It_Id.getText().toString()))){

                        DB.DeleteOne(Integer.parseInt(It_Id.getText().toString()));
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);

                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Delete.this);
                    builder.setTitle("Warning");
                    builder.setMessage("Can't delete the tool, input is wrong");
                    builder.setPositiveButton("Return", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                            startActivity(intent);
                        }
                    });
                    builder.setNegativeButton("cancel",null);

                    builder.show();



                }
            }
        });





    }
}