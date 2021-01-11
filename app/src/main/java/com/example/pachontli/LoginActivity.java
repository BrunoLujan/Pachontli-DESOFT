package com.example.pachontli;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.pachontli.requests.LoginRequest;
import com.example.pachontli.responses.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText txtEmail, txtPassword;
    Button btnLogin, btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Connection.getUserService();

        txtEmail = (EditText) findViewById(R.id.txtEmailLogin);
        txtPassword = (EditText) findViewById(R.id.txtPasswordLogin);
        btnLogin = (Button) findViewById(R.id.btnLoginLogin);
        btnRegister = (Button) findViewById(R.id.btnRegisterLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateBlankSpaces()){
                    login();
                } else {
                    Connection.Message(getApplicationContext(),"Complete all fields");
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() { }

    private boolean validateBlankSpaces() {
        if(txtEmail.getText().toString().isEmpty()) {
            return false;
        }

        if(txtPassword.getText().toString().isEmpty()) {
            return false;
        }

        return true;
    }

    private  void login() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(txtEmail.getText().toString());
        loginRequest.setPassword(txtPassword.getText().toString());

        Call<LoginResponse> loginResponseCall = Connection.CONNECTION.login(loginRequest);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {

            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()) {
                    Connection.AUTHTOKEN = "Bearer " + response.body().getToken();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }else
                    Connection.Message(getApplicationContext(),"Email/Password is incorrect");
            }
            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Connection.Message(getApplicationContext(), t.getMessage());
                Log.d("ERROR-LoginActivity-login-onFailure", t.getMessage());
            }
        });
    }
}