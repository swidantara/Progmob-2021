package com.cy.swidantarawidget.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cy.swidantarawidget.GetSet.GetSetUser;
import com.cy.swidantarawidget.R;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

public class AdapterUser extends RecyclerView.Adapter<AdapterUser.ViewHolder> {

    private List<GetSetUser> itemLists;
    private Context context;

    public AdapterUser(List<GetSetUser> itemLists, Context context){
        this.itemLists = itemLists;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.templates_data, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GetSetUser getSetItem = itemLists.get(position);

        holder.nama.setText("Nama : " + getSetItem.getNama());
        holder.telepon.setText("Telepon : " + getSetItem.getTelepon());
        holder.alamat.setText("Alamat : " + getSetItem.getAlamat());
        holder.jenisKelamin.setText("Jenis Kelamin : " + getSetItem.getJk());
        holder.umur.setText("Umur : " + getSetItem.getUmur() + " Tahun");
    }

    @Override
    public int getItemCount() {
        return itemLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public MaterialTextView nama, telepon, alamat, jenisKelamin, umur;

        public ViewHolder(View view){
            super(view);

            nama = view.findViewById(R.id.nama);
            telepon = view.findViewById(R.id.telepon);
            alamat = view.findViewById(R.id.alamat);
            jenisKelamin = view.findViewById(R.id.jenisKelamin);
            umur = view.findViewById(R.id.umur);
        }
    }
}
