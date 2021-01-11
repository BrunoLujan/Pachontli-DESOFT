package com.example.pachontli;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        btnRegister = (Button) findViewById(R.id.btnRegisterRegister);
        btnLogin = (Button) findViewById(R.id.btnLoginRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateBlankFields()){
                    if (txtPassword.length() >= 8) {
                        register();
                    } else {
                        Connection.Message(getApplicationContext(), "Password has to be greater than 8 characters");
                    }
                } else {
                    Connection.Message(getApplicationContext(),"Complete all fields");
                }
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

    private boolean validateBlankFields() {
        if (txtName.getText().toString().isEmpty()) {
            return false;
        }

        if (txtLastName.getText().toString().isEmpty()) {
            return false;
        }

        if (txtEmail.getText().toString().isEmpty()) {
            return false;
        }

        if (txtCellphone.getText().toString().isEmpty()) {
            return false;
        }

        if (txtPassword.getText().toString().isEmpty()) {
            return false;
        }

        return true;
    }

    private void register() {
        Call<ResponseBody> call = Connection.CONNECTION.signUpClient(
                txtName.getText().toString(),
                txtLastName.getText().toString(),
                txtEmail.getText().toString(),
                txtCellphone.getText().toString(),
                txtPassword.getText().toString()
        );
        call.enqueue(new Callback<ResponseBody>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Connection.Message(getApplicationContext(), "Client has been added");
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    Connection.Message(getApplicationContext(), "Error, try again: " + response.message());
                    Log.d("ERROR-RegisterProfileActivity-register-onResponse", response.message());
                }
            }

            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Connection.Message(getApplicationContext(), t.getMessage());
                Log.d("ERROR-RegisterProfileActivity-register-onFailure", t.getMessage());
            }
        });
    }
}