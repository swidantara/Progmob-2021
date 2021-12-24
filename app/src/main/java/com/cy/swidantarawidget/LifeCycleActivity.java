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

public class LifeCycleActivity extends AppCompatActivity {

    private EditText textNama, textTelepon, textAlamat, textUmur;
    private Button buttonClose;
    private RadioButton radioButtonLakiLaki, radioButtonPerempuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle);

        Intent getIntentData = getIntent();
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
        if(jenis_kelamin.equals("Laki - laki")){
            radioButtonLakiLaki.setChecked(true);
        }else{
            radioButtonPerempuan.setChecked(true);
        }
        buttonClose = findViewById(R.id.buttonClose);
        buttonClose.setOnClickListener(view -> {
            startActivity(new Intent(LifeCycleActivity.this, MainActivity.class));
            finish();
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(LifeCycleActivity.this, MainActivity.class));
        finish();
    }

    @Override
    protected void onDestroy() {
        Toast.makeText(LifeCycleActivity.this, "Selamat Tinggal. Anda Kembali ke halaman utama.", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        Toast.makeText(LifeCycleActivity.this, "Selamat Tinggal. Anda Kembali ke halaman utama.", Toast.LENGTH_SHORT).show();
        super.onStop();
    }
}
