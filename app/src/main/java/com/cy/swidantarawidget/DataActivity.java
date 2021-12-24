package com.cy.swidantarawidget;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cy.swidantarawidget.Adapter.AdapterUser;
import com.cy.swidantarawidget.GetSet.GetSetUser;

import java.util.ArrayList;
import java.util.List;

public class DataActivity extends AppCompatActivity {

    Database DB;
    List<GetSetUser> listDataUser;
    AdapterUser adapterUser;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        DB = new Database(this);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(DataActivity.this));
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(DataActivity.this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        int itemPosition = recyclerView.getChildLayoutPosition(view);
                        final GetSetUser item = listDataUser.get(itemPosition);
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                                DataActivity.this);
                        alertDialogBuilder.setTitle("Warning");

                        alertDialogBuilder
                                .setMessage("Apakah Anda ingin menghapus data dengan nama " + item.getNama() + " ?")
                                .setIcon(R.mipmap.ic_launcher)
                                .setCancelable(false)
                                .setPositiveButton("Ya", (dialog, id) -> {
                                    DB.deleteUser(item.getId());
                                    Toast.makeText(DataActivity.this, "Berhasil menghapus data.", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(DataActivity.this, DataActivity.class));
                                    finish();
                                })
                                .setNegativeButton("Tidak", (dialogInterface, i) -> {

                                });

                        AlertDialog alertDialog = alertDialogBuilder.create();

                        alertDialog.show();

                    }

                    @Override public void onLongItemClick(View view, int position) {
                        int itemPosition = recyclerView.getChildLayoutPosition(view);
                        final GetSetUser item = listDataUser.get(itemPosition);
                        Intent intent = new Intent(DataActivity.this, UpdateActivity.class);
                        intent.putExtra("id", item.getId());
                        intent.putExtra("nama", item.getNama());
                        intent.putExtra("telepon", item.getTelepon());
                        intent.putExtra("alamat", item.getAlamat());
                        intent.putExtra("jenis_kelamin", item.getJk());
                        intent.putExtra("umur", item.getUmur());
                        startActivity(intent);
                        finish();
                    }
                })
        );

        Cursor res = DB.getDataUser();
        if(res.getCount()==0){
            Toast.makeText(DataActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
            return;
        }
        listDataUser = new ArrayList<>();
        while(res.moveToNext()){
            listDataUser.add(new GetSetUser(res.getString(0),
                    res.getString(1),
                    res.getString(2),
                    res.getString(3),
                    res.getString(4),
                    res.getString(5)));
        }

        adapterUser = new AdapterUser(listDataUser, DataActivity.this);
        recyclerView.setAdapter(adapterUser);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}