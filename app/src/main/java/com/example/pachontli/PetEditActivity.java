package com.example.pachontli;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.pachontli.models.Pet;

public class PetEditActivity extends AppCompatActivity {

    Intent intent;

    Pet petSelected = new Pet();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_edit);

        initializePetSelected();
        Connection.Message(getApplicationContext(), "Pet has been selected " + petSelected.getNombre());
    }

    private void initializePetSelected() {
        intent = getIntent();
        petSelected.setId(intent.getIntExtra("petSelectedId", 0));
        petSelected.setUser_id(intent.getIntExtra("petSelectedUserId", 0));
        petSelected.setNombre(intent.getStringExtra("petSelectedName"));
        petSelected.setSexo(intent.getStringExtra("petSelectedGender"));
        petSelected.setEspecie(intent.getStringExtra("petSelectedSpecie"));
        petSelected.setRaza(intent.getStringExtra("petSelectedBreed"));
        petSelected.setPeso(intent.getStringExtra("petSelectedWeight"));
        petSelected.setAltura(intent.getFloatExtra("petSelectedHeight", 0));
        petSelected.setEdad(intent.getIntExtra("petSelectedAge", 0));
        petSelected.setDescripcion(intent.getStringExtra("petSelectedDescription"));
    }
}