package com.example.pachontli.ui.pets;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.pachontli.PetRegisterActivity;
import com.example.pachontli.R;

public class PetsFragment extends Fragment {

    View viewPetsFragment;

    Button btnRegisterPet;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        viewPetsFragment = inflater.inflate(R.layout.fragment_pets,container,false);

        btnRegisterPet = (Button) viewPetsFragment.findViewById(R.id.btnRegisterPets);

        btnRegisterPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PetRegisterActivity.class);
                startActivity(intent);
            }
        });

        return viewPetsFragment;
    }
}