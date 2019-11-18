package com.example.easymarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ProfileUtamaActivity extends AppCompatActivity {


    ImageView img;
    TextView tvNama,tvUmur,tvGender,tvEmail,tvDaerahAsal;
    ArrayList<User> listUser = new ArrayList<>();
    ArrayList<Barang> listBarang = new ArrayList<>();
    ArrayList<Toko> listToko = new ArrayList<>();
    ArrayList<ClassWishlist> listWishlist = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_utama);

        img = findViewById(R.id.imgViewProfileUtamaActivity);
        tvNama = findViewById(R.id.tvNamProfilUtamaActivity);
        tvUmur = findViewById(R.id.tvUmurProfileUtamaActivity);
        tvEmail = findViewById(R.id.tvEmailProfileUserActivity);
        tvGender = findViewById(R.id.tvGenderProfileUtamaActivity);
        tvDaerahAsal = findViewById(R.id.tvDaerahAsalProfileUserActivity);



        Intent j = getIntent();
        listUser= (ArrayList<User>) j.getSerializableExtra("listUser");
        listToko= (ArrayList<Toko>) j.getSerializableExtra("listToko");
        listBarang= (ArrayList<Barang>) j.getSerializableExtra("listBarang");
        listWishlist= (ArrayList<ClassWishlist>) j.getSerializableExtra("listWishlist");
        Toast.makeText(this, listUser.size()+"", Toast.LENGTH_SHORT).show();

        // masukinData User yang aktif
        for (int i = 0; i < listUser.size(); i++) {
            if(listUser.get(i).getAktif().equals("1")){
                tvNama.setText("Nama : "+listUser.get(i).getNama());
                tvUmur.setText("Umur : "+listUser.get(i).getUmur()+" Thn");
                tvEmail.setText("Email : "+listUser.get(i).getEmail());
                tvGender.setText("Gender : "+listUser.get(i).getGender());
                tvDaerahAsal.setText("Daerah : "+listUser.get(i).getDaerahasal());
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
            Intent a = new Intent(ProfileUtamaActivity.this, EditProfileActivity.class);
            a.putExtra("listUser", listUser);
            a.putExtra("listBarang", listBarang);
            a.putExtra("listWishlist", listWishlist);
            a.putExtra("listToko", listToko);
            startActivity(a);
        }
        int id = item.getItemId();
        if(id== android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    

}
