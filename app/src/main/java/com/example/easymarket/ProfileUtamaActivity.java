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

public class ProfileUtamaActivity extends AppCompatActivity {


    ImageView img;
    TextView tvNama,tvUmur,tvGender,tvEmail,tvDaerahAsal;


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
