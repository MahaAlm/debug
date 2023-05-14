package com.example.debug;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


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
                }
            }
        });





    }
}