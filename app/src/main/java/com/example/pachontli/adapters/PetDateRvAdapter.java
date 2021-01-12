package com.example.pachontli.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pachontli.R;
import com.example.pachontli.models.Pet;

import java.util.List;

public class PetDateRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements View.OnClickListener {

    List<Pet> pets;
    View.OnClickListener clickListener;

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvSpecie;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName =  itemView.findViewById(R.id.tvNamePetsDateListRow);
            tvSpecie= itemView.findViewById(R.id.tvSpeciePetsDateListRow);
        }
    }

    public PetDateRvAdapter(List<Pet> pets) {
        this.pets = pets;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.pets_date_list_row, parent,
                false);

        PetDateRvAdapter.ViewHolder vh = new PetDateRvAdapter.ViewHolder(v);
        v.setOnClickListener(this);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        PetDateRvAdapter.ViewHolder vh = (PetDateRvAdapter.ViewHolder) holder;

        vh.tvName.setText(pets.get(position).getNombre());
        vh.tvSpecie.setText(pets.get(position).getEspecie());
    }

    @Override
    public int getItemCount() {
        return pets.size();
    }

    @Override
    public void onClick(View v) {
        if(clickListener != null) {
            clickListener.onClick(v);
        }
    }

    public void setOnClickListener(View.OnClickListener clickListener) {
        this.clickListener = clickListener;
    }
}
