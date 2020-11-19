package com.example.treep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
EditText username;
EditText password;
Button login;
TextView register;

DatabaseHelp db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db= new DatabaseHelp(this);
        username=(EditText)findViewById(R.id.EditText_username);
        password=(EditText)findViewById(R.id.EditText_password);
        login=(Button) findViewById(R.id.Button_login);
        register=(TextView)findViewById(R.id.TextView_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registering = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(registering);

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString().trim();
                String pwd = password.getText().toString().trim();
                Boolean res = db.checkUser(user,pwd);
                if (res == true){
                    Intent movetohomepage = new Intent(LoginActivity.this,HomepageActivity.class);
                    startActivity(movetohomepage);


            }
                else {
                    Toast.makeText(LoginActivity.this,"Login Error",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}