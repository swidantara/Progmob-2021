package com.cy.swidantarawidget;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {

    private EditText textNama, textTelepon, textAlamat;
    private TextView textUmur;
    private RadioGroup radioGroup;
    private String jenisKelamin = "";
    private SeekBar seekBarUmur;
    private Button buttonDaftar;
    private RadioButton radioButtonLakiLaki, radioButtonPerempuan;

    Database DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        DB = new Database(this);
        Intent getIntentData = getIntent();
        String iduser = getIntentData.getStringExtra("id");
        String nama = getIntentData.getStringExtra("nama");
        String telepon = getIntentData.getStringExtra("telepon");
        String alamat = getIntentData.getStringExtra("alamat");
        String umur = getIntentData.getStringExtra("umur");
        String jenis_kelamin = getIntentData.getStringExtra("jenis_kelamin");

        textNama = findViewById(R.id.textNama);
        textNama.setText(nama);
        textTelepon = findViewById(R.id.textTelepon);
        textTelepon.setText(telepon);
        textAlamat = findViewById(R.id.textAlamat);
        textAlamat.setText(alamat);
        textUmur = findViewById(R.id.textUmur);
        textUmur.setText(umur);
        radioButtonLakiLaki = findViewById(R.id.radioButtonLakiLaki);
        radioButtonPerempuan = findViewById(R.id.radioButtonPerempuan);
        seekBarUmur = findViewById(R.id.seekBarUmur);
        seekBarUmur.setProgress(Integer.parseInt(umur));
        seekBarUmur.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                textUmur.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        radioGroup = findViewById(R.id.radioGroup);
        //fungsi untuk radio group dimana pada saat isi dari radio grup yang isinya reguler / large dilakukan klik maka variabel ukuran akan diisikan dengan value tersebut
        radioGroup.setOnCheckedChangeListener((radioGroup, id) -> {
            switch (id){
                case R.id.radioButtonLakiLaki:
                    jenisKelamin = "Laki - laki";
                    break;
                case R.id.radioButtonPerempuan:
                    jenisKelamin = "Perempuan";
                    break;
            }
        });

        buttonDaftar = findViewById(R.id.buttonDaftar);
        buttonDaftar.setOnClickListener(view -> {
            if(!jenisKelamin.equals("")){
                boolean check = DB.updateUser(
                        iduser,
                        textNama.getText().toString(),
                        textTelepon.getText().toString(),
                        textAlamat.getText().toString(),
                        jenisKelamin,
                        Integer.parseInt(textUmur.getText().toString())
                );
                if (check){
                    Toast.makeText(UpdateActivity.this, "Berhasil merubah data User ke SQLite", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(UpdateActivity.this, DataActivity.class));
                    finish();
                }else{
                    Toast.makeText(UpdateActivity.this, "Gagal merubah data User ke SQLite", Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(UpdateActivity.this, "Pilih jenis kelamin Anda terlebih dahulu.", Toast.LENGTH_SHORT).show();
            }
        });

        if(jenis_kelamin.equals("Laki - laki")){
            radioButtonLakiLaki.setChecked(true);
        }else{
            radioButtonPerempuan.setChecked(true);
        }
    }
}