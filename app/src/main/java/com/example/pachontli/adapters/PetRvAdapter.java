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

public class PetRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements View.OnClickListener {

    List<Pet> pets;
    View.OnClickListener clickListener;

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivImage;
        TextView tvName;
        TextView tvSpecie;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.ivPetsListRow);
            tvName =  itemView.findViewById(R.id.tvNamePetsListRow);
            tvSpecie= itemView.findViewById(R.id.tvSpeciePetsListRow);
        }
    }

    public PetRvAdapter(List<Pet> pets) {
        this.pets = pets;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.pets_list_row, parent,
                false);

        PetRvAdapter.ViewHolder vh = new PetRvAdapter.ViewHolder(v);
        v.setOnClickListener(this);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        PetRvAdapter.ViewHolder vh = (PetRvAdapter.ViewHolder) holder;

        if(pets.get(position).getEspecie().equals("Dog"))
            vh.ivImage.setImageResource(R.drawable.ic_dog_black);

        if(pets.get(position).getEspecie().equals("Cat"))
            vh.ivImage.setImageResource(R.drawable.ic_cat_black);

        if(pets.get(position).getEspecie().equals("Bird"))
            vh.ivImage.setImageResource(R.drawable.ic_bird_black);

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
