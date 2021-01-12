package com.example.pachontli.ui.pets;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pachontli.Connection;
import com.example.pachontli.PetEditActivity;
import com.example.pachontli.PetRegisterActivity;
import com.example.pachontli.R;
import com.example.pachontli.adapters.PetRvAdapter;
import com.example.pachontli.models.Pet;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PetsFragment extends Fragment {

    View viewPetsFragment;
    RecyclerView rv;
    PetRvAdapter adapter;
    List<Pet> petList;

    Button btnRegisterPet;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        viewPetsFragment = inflater.inflate(R.layout.fragment_pets,container,false);
        rv = viewPetsFragment.findViewById(R.id.rvPetsFragment);

        btnRegisterPet = (Button) viewPetsFragment.findViewById(R.id.btnRegisterPetsFragment);

        btnRegisterPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PetRegisterActivity.class);
                startActivity(intent);
            }
        });

        getPets();

        return viewPetsFragment;
    }

    public  void getPets() {
        Call<List<Pet>> call = Connection.CONNECTION.getPets(Connection.AUTHTOKEN);
        call.enqueue(new Callback<List<Pet>>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(Call<List<Pet>> call, Response<List<Pet>> response) {
                if (response.isSuccessful()) {
                    petList = response.body();
                    buildRV();
                } else {
                    Connection.Message(getContext(), "Error, try again: " + response.message());
                    Log.d("ERROR-PetsFragment-getPets-onResponse", response.message());
                }
            }

            @SuppressLint("LongLogTag")
            @Override
            public void onFailure(Call<List<Pet>> call, Throwable t) {
                Connection.Message(getContext(), t.getMessage());
                Log.d("ERROR-PetsFragment-getPets-onFailure", t.getMessage());
            }
        });
    }

    public void buildRV() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);

        adapter = new PetRvAdapter(petList);

        Intent intent = new Intent(getActivity(), PetEditActivity.class);
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("petSelectedId", petList.get
                        (rv.getChildAdapterPosition(v)).getId());
                intent.putExtra("petSelectedUserId", petList.get
                        (rv.getChildAdapterPosition(v)).getUser_id());
                intent.putExtra("petSelectedName", petList.get
                        (rv.getChildAdapterPosition(v)).getNombre());
                intent.putExtra("petSelectedGender", petList.get
                        (rv.getChildAdapterPosition(v)).getSexo());
                intent.putExtra("petSelectedSpecie", petList.get
                        (rv.getChildAdapterPosition(v)).getEspecie());
                intent.putExtra("petSelectedBreed", petList.get
                        (rv.getChildAdapterPosition(v)).getRaza());
                intent.putExtra("petSelectedWeight", petList.get
                        (rv.getChildAdapterPosition(v)).getPeso());
                intent.putExtra("petSelectedHeight", petList.get
                        (rv.getChildAdapterPosition(v)).getAltura());
                intent.putExtra("petSelectedAge", petList.get
                        (rv.getChildAdapterPosition(v)).getEdad());
                intent.putExtra("petSelectedDescription", petList.get
                        (rv.getChildAdapterPosition(v)).getDescripcion());
                startActivity(intent);
            }
        });

        rv.setAdapter(adapter);
    }
}