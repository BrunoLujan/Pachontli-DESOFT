package com.example.pachontli;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    EditText txtName, txtLastName, txtEmail, txtCellphone, txtPassword;
    Button btnRegister, btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtName = (EditText) findViewById(R.id.txtNameRegister);
        txtLastName = (EditText) findViewById(R.id.txtLastNameRegister);
        txtEmail = (EditText) findViewById(R.id.txtEmailRegister);
        txtCellphone = (EditText) findViewById(R.id.txtCellphoneRegister);
        txtPassword = (EditText) findViewById(R.id.txtPasswordRegister);
        btnRegister = (Button) findViewById(R.id.btnRegiterRegister);
        btnLogin = (Button) findViewById(R.id.btnLoginRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}