package com.example.easymarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class EditProfileActivity extends AppCompatActivity {

    ImageView img;
    EditText tvNama,tvPasswordBaru,tvConfirmPassword;
    Button btnUpdate;
    String PasswordBaru = "";
    String PasswordConfirmBaru = "";
    ArrayList<User> listUser = new ArrayList<>();
    ArrayList<Barang> listBarang = new ArrayList<>();
    ArrayList<Toko> listToko = new ArrayList<>();
    ArrayList<ClassWishlist> listWishlist = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        tvNama = findViewById(R.id.tvNamaEditProfile);
        tvPasswordBaru = findViewById(R.id.tvPasswordBaruEditProfile);
        tvConfirmPassword = findViewById(R.id.tvConfirmPasswordBaruEditProfile);
        btnUpdate = findViewById(R.id.btnUpdateEditProfile);

        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setCornerRadius(100);
        drawable.setColor(Color.WHITE);
        tvNama.setBackground(drawable);
        tvPasswordBaru.setBackground(drawable);
        tvConfirmPassword.setBackground(drawable);

        GradientDrawable drawable2 = new GradientDrawable();
        drawable2.setShape(GradientDrawable.RECTANGLE);
        drawable2.setCornerRadius(100);
        drawable2.setColor(Color.BLACK);
        btnUpdate.setBackground(drawable2);

        Intent j = getIntent();
        listUser= (ArrayList<User>) j.getSerializableExtra("listUser");
        listToko= (ArrayList<Toko>) j.getSerializableExtra("listToko");
        listBarang= (ArrayList<Barang>) j.getSerializableExtra("listBarang");
        listWishlist= (ArrayList<ClassWishlist>) j.getSerializableExtra("listWishlist");
        // masukinData User yang aktif
        // masukinData User yang aktif
        for (int i = 0; i < listUser.size(); i++) {
            if(listUser.get(i).getAktif().equals("1")){
                tvNama.setText(listUser.get(i).getNama());
            }
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Update();
            }
        });

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public void Update(){
        String nama = "";
        String pass = "";
        int ctr = 0;
        PasswordBaru = tvPasswordBaru.getText().toString();
        PasswordConfirmBaru = tvConfirmPassword.getText().toString();
        if(!tvNama.equals("") || !tvPasswordBaru.equals("")){
            if(PasswordBaru.equals(PasswordConfirmBaru)){
                for (int i = 0; i < listUser.size(); i++) {
                    if(listUser.get(i).getAktif().equals("1")){
                        listUser.get(i).setNama(tvNama.getText().toString());
                        listUser.get(i).setPassword(tvPasswordBaru.getText().toString());
                        Toast.makeText(this, "Berhasil", Toast.LENGTH_SHORT).show();
                    }
                }

            }
            else Toast.makeText(this, "Password Baru dan Confirm Password Baru Tidak Sama", Toast.LENGTH_SHORT).show();
        }
        else Toast.makeText(this, "isi semua field terlebih dahulu", Toast.LENGTH_SHORT).show();



    }
    public void Upload(){ // uploadGambar

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id== android.R.id.home){
            Intent a = new Intent(EditProfileActivity.this, ProfileUtamaActivity.class);
            a.putExtra("listUser", listUser);
            a.putExtra("listBarang", listBarang);
            a.putExtra("listWishlist", listWishlist);
            a.putExtra("listToko", listToko);
            startActivity(a);
        }
        return super.onOptionsItemSelected(item);
    }

}
