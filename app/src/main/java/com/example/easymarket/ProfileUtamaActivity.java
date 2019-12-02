package com.example.easymarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ProfileUtamaActivity extends AppCompatActivity {


    ImageView img;
    TextView tvNama,tvUmur,tvGender,tvDaerahAsal, tvEmail;
    Button simpan;
    ArrayList<User> listUser = new ArrayList<>();
    ArrayList<Barang> listBarang = new ArrayList<>();
    ArrayList<Toko> listToko = new ArrayList<>();
    ArrayList<ClassWishlist> listWishlist = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_utama);

        img = findViewById(R.id.imgViewProfileUtamaActivity);
        tvNama = findViewById(R.id.etNamProfilUtamaActivity);
        tvUmur = findViewById(R.id.tvShowUmur);
        tvGender = findViewById(R.id.tvShowGender);
        tvDaerahAsal = findViewById(R.id.tvShowDaerah);
        tvEmail = findViewById(R.id.tvShowEmail);
        simpan = findViewById(R.id.btSimpanProfile);

        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setCornerRadius(100);
        drawable.setColor(Color.WHITE);
        tvNama.setBackground(drawable);

        GradientDrawable drawable2 = new GradientDrawable();
        drawable2.setShape(GradientDrawable.RECTANGLE);
        drawable2.setCornerRadius(100);
        drawable2.setColor(Color.BLACK);
        simpan.setBackground(drawable2);

        Intent j = getIntent();
        listUser= (ArrayList<User>) j.getSerializableExtra("listUser");
        listToko= (ArrayList<Toko>) j.getSerializableExtra("listToko");
        listBarang= (ArrayList<Barang>) j.getSerializableExtra("listBarang");
        listWishlist= (ArrayList<ClassWishlist>) j.getSerializableExtra("listWishlist");

        for (int i = 0; i < listUser.size(); i++) {
            if(listUser.get(i).getAktif().equals("1")){
                int indexalamatemail = listUser.get(i).getEmail().indexOf("@");
                String hiddenemail =listUser.get(i).getEmail().substring(0,2)+"******"+listUser.get(i).getEmail().substring(indexalamatemail,listUser.get(i).email.length());
                tvNama.setText(listUser.get(i).getNama());
                tvUmur.setText(listUser.get(i).getUmur()+" Thn");
                tvGender.setText(listUser.get(i).getGender());
                tvDaerahAsal.setText(listUser.get(i).getDaerahasal());
                tvEmail.setText(hiddenemail);
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
