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
import com.example.pachontli.models.Veterinarian;

import java.util.List;

public class VeterinarianDateRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements View.OnClickListener {

    List<Veterinarian> veterinarians;
    View.OnClickListener clickListener;

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvNames;
        TextView tvCedula;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNames =  itemView.findViewById(R.id.tvNamesVeterinariansDateListRow);
            tvCedula = itemView.findViewById(R.id.tvCedulaVeterinariansDateListRow);
        }
    }

    public VeterinarianDateRvAdapter(List<Veterinarian> veterinarians) {
        this.veterinarians = veterinarians;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.veterinarians_date_list_row, parent,
                false);

        VeterinarianDateRvAdapter.ViewHolder vh = new VeterinarianDateRvAdapter.ViewHolder(v);
        v.setOnClickListener(this);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VeterinarianDateRvAdapter.ViewHolder vh = (VeterinarianDateRvAdapter.ViewHolder) holder;

        vh.tvNames.setText(veterinarians.get(position).getNombre() + " " + veterinarians.get(position).getApellidos());
        vh.tvCedula.setText(veterinarians.get(position).getCedula());
    }

    @Override
    public int getItemCount() {
        return veterinarians.size();
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
