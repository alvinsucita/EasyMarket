package com.example.easymarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ProfileUtamaActivity extends AppCompatActivity {


    ImageView img;
    TextView tvNama,tvUmur,tvGender,tvEmail,tvDaerahAsal;
    ArrayList<User> listUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_utama);

        img = findViewById(R.id.imgViewProfileUtamaActivity);
        tvNama = findViewById(R.id.tvNamProfilUtamaActivity);
        tvUmur = findViewById(R.id.tvUmurProfileUtamaActivity);
        tvEmail = findViewById(R.id.tvUmurProfileUtamaActivity);
        tvGender = findViewById(R.id.tvGenderProfileUtamaActivity);
        tvDaerahAsal = findViewById(R.id.tvDaerahAsalUserProfileActivity);

        // ambil data static list user dari homeActivity dimasukkan ke listUser
        listUser = Home.listUser;
        // masukinData User yang aktif
        for (int i = 0; i < listUser.size(); i++) {
            if(listUser.get(i).getAktif() == "1"){
                tvNama.setText(listUser.get(i).getNama());
                tvUmur.setText(listUser.get(i).getUmur());
                tvEmail.setText(listUser.get(i).getEmail());
                tvGender.setText(listUser.get(i).getGender());
                tvDaerahAsal.setText(listUser.get(i).getDaerahasal());
            }
        }

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_edit_profile,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.item_edit_profile){
            Intent i = new Intent(ProfileUtamaActivity.this,EditProfileActivity.class);
            startActivity(i);
        }
        int id = item.getItemId();
        if(id== android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    

}
