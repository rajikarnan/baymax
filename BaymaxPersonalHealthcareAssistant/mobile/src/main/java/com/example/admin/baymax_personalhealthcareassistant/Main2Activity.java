package com.example.admin.baymax_personalhealthcareassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    Button mb;
    EditText mTextUsername;
    EditText mTextPassword;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        db = new DatabaseHelper(this);
        mTextUsername = (EditText)findViewById(R.id.editText);
        mTextPassword = (EditText)findViewById(R.id.password);
        mb=(Button)findViewById(R.id.button3);
        mb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = mTextUsername.getText().toString().trim();
                String pwd = mTextPassword.getText().toString().trim();
                Boolean res = db.checkUser(user,pwd);
                if ( res==true )
                {
                    //Toast.makeText(Main2Activity.this,"Success",Toast.LENGTH_SHORT).show();
                   Intent login = new Intent(Main2Activity.this, Main5Activity.class);
                   startActivity(login);
                }
                else
                {
                    Toast.makeText(Main2Activity.this,"INVALID LOGIN CREDENTIALS",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
