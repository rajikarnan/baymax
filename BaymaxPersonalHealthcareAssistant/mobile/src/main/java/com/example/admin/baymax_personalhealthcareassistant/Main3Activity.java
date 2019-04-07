package com.example.admin.baymax_personalhealthcareassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {
    Button b;
    EditText TextUsername;
    EditText TextPassword;
    EditText mail;
    EditText TextcnfPassword;
    EditText Phone;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        db = new DatabaseHelper(this);
        TextUsername = (EditText)findViewById(R.id.editText);
        TextPassword = (EditText)findViewById(R.id.editText4);
        TextcnfPassword = (EditText)findViewById(R.id.editText5);
        mail = (EditText)findViewById(R.id.editText6);
        Phone = (EditText)findViewById(R.id.editText7);
        b=(Button)findViewById(R.id.b4);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = TextUsername.getText().toString().trim();
                String pwd = TextPassword.getText().toString().trim();
                String cnfpwd = TextcnfPassword.getText().toString().trim();
                String email = mail.getText().toString().trim();
                String phn = Phone.getText().toString().trim();
                if(pwd.equals(cnfpwd))
                {
                    long val = db.addUser(user,pwd,email,phn);
                    if (val > 0) {
                        Toast.makeText(Main3Activity.this, "SUCCESSFULLY REGISTERED", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                        startActivity(intent);
                    }

                    else
                    {
                        Toast.makeText(Main3Activity.this, "REGISTRATION ERROR", Toast.LENGTH_SHORT).show();
                    }

                }
                else
                {
                    Toast.makeText(Main3Activity.this,"PASSWORD DO NOT MATCH",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
