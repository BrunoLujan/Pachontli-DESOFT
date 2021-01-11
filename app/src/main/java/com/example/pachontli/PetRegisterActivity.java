package com.example.pachontli;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.pachontli.requests.RegisterPetRequest;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PetRegisterActivity extends AppCompatActivity {

    EditText txtName, txtGender, txtSpecie, txtBreed, txtAge, txtWeight, txtHeight, txtDescription;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_register);

        txtName = (EditText) findViewById(R.id.txtNamePetRegister);
        txtGender = (EditText) findViewById(R.id.txtGenderPetRegister);
        txtSpecie = (EditText) findViewById(R.id.txtSpeciePetRegister);
        txtBreed = (EditText) findViewById(R.id.txtBreedPetRegister);
        txtAge = (EditText) findViewById(R.id.txtAgePetRegister);
        txtWeight = (EditText) findViewById(R.id.txtWeightPetRegister);
        txtHeight = (EditText) findViewById(R.id.txtHeightPetRegister);
        txtDescription = (EditText) findViewById(R.id.txtDescriptionPetRegister);
        btnRegister = (Button) findViewById(R.id.btnRegisterPetRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateBlankSpaces()) {
                    registerPet();
                } else {
                    Connection.Message(getApplicationContext(),"Complete all fields");
                }
            }
        });
    }

    private boolean validateBlankSpaces() {
        if(txtName.getText().toString().isEmpty()){
            return false;
        }

        if(txtGender.getText().toString().isEmpty()){
            return false;
        }

        if(txtSpecie.getText().toString().isEmpty()){
            return false;
        }

        if(txtBreed.getText().toString().isEmpty()){
            return false;
        }

        if(txtWeight.getText().toString().isEmpty()){
            return false;
        }

        if(txtHeight.getText().toString().isEmpty()){
            return false;
        }

        if(txtAge.getText().toString().isEmpty()){
            return false;
        }

        if(txtDescription.getText().toString().isEmpty()){
            return false;
        }

        return true;
    }

    private void registerPet() {
        RegisterPetRequest pet = new RegisterPetRequest();

        pet.setNombre(txtName.getText().toString());
        pet.setSexo(txtGender.getText().toString());
        pet.setEspecie(txtSpecie.getText().toString());
        pet.setRaza(txtBreed.getText().toString());
        pet.setPeso(txtWeight.getText().toString());
        pet.setAltura(Float.parseFloat(txtHeight.getText().toString()));
        pet.setEdad(Integer.parseInt(txtAge.getText().toString()));
        pet.setDescripcion(txtDescription.getText().toString());

        Call<ResponseBody> call = Connection.CONNECTION.registerPet(Connection.AUTHTOKEN ,pet);
        call.enqueue(new Callback<ResponseBody>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Connection.Message(getApplicationContext(), "Pet has been registered");
                    Intent intent = new Intent(PetRegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Connection.Message(getApplicationContext(), "Error, try again: " + response.message());
                    Log.d("ERROR-PetRegisterActivity-registerPet-onResponse", response.message());
                }
            }

            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Connection.Message(getApplicationContext(), t.getMessage());
                Log.d("ERROR-RPetRegisterActivity-registerPet-onFailure", t.getMessage());
            }
        });
    }
}