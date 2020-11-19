package com.example.treep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    EditText confirmpassword;
    Button register;
    TextView login;
    DatabaseHelp db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db = new DatabaseHelp(this);
        username=(EditText)findViewById(R.id.EditText_username);
        password=(EditText)findViewById(R.id.EditText_password);
        confirmpassword=(EditText)findViewById(R.id.EditText_confirmpassword);
       register=(Button) findViewById(R.id.Button_register);
        login=(TextView)findViewById(R.id.TextView_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login_intent= new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(login_intent);
            }
        });
       register.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String user= username.getText().toString().trim();
               String pass = password.getText().toString().trim();
               String cmf_pass= confirmpassword.getText().toString().trim();
               if(pass.equals(cmf_pass)) {
                   long val = db.adduser(user,pass);
                   if(val>0){
                       Toast.makeText(RegisterActivity.this,"You have registered ",Toast.LENGTH_SHORT).show();
                       Intent movetologin = new Intent(RegisterActivity.this,LoginActivity.class);
                       startActivity(movetologin);
                   }
                   else{
                       Toast.makeText(RegisterActivity.this,"Registration Error ",Toast.LENGTH_SHORT).show();
                   }


               }
               else{
                   Toast.makeText(RegisterActivity.this,"Password is not matching",Toast.LENGTH_SHORT).show();

               }
           }
       });
    }
}