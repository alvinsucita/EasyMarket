package com.example.easymarket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.collection.LLRBNode;

import java.util.ArrayList;

public class Login extends AppCompatActivity {
    EditText email,pass;
    Button login;
    String stremail,strpass;
    ArrayList<User> listUser = new ArrayList<>();
    ArrayList<Toko> listToko = new ArrayList<>();
    ArrayList<Barang> listBarang = new ArrayList<>();
    ArrayList<ClassWishlist> listWishlist = new ArrayList<>();
    ArrayList<ClassRequestLelang> listRequestLelang = new ArrayList<>();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=findViewById(R.id.etEmail);
        pass=findViewById(R.id.etPassword);
        login=findViewById(R.id.btnLogin);

        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setCornerRadius(100);
        drawable.setColor(Color.WHITE);
        email.setBackground(drawable);

        GradientDrawable drawable2 = new GradientDrawable();
        drawable2.setShape(GradientDrawable.RECTANGLE);
        drawable2.setCornerRadius(100);
        drawable2.setColor(Color.WHITE);
        pass.setBackground(drawable2);

        GradientDrawable drawable3 = new GradientDrawable();
        drawable3.setShape(GradientDrawable.RECTANGLE);
        drawable3.setCornerRadius(100);
        drawable3.setColor(Color.BLACK);
        login.setBackground(drawable3);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        if(i.hasExtra("listUser")){
            listUser= (ArrayList<User>) i.getSerializableExtra("listUser");
            listToko= (ArrayList<Toko>) i.getSerializableExtra("listToko");
            listBarang= (ArrayList<Barang>) i.getSerializableExtra("listBarang");
            listWishlist= (ArrayList<ClassWishlist>) i.getSerializableExtra("listWishlist");
            listRequestLelang= (ArrayList<ClassRequestLelang>) i.getSerializableExtra("listRequestLelang");
        }
    }

    public void toRegis(View view) {
        Intent i = new Intent(Login.this, Register.class);
        i.putExtra("listUser", listUser);
        i.putExtra("listToko", listToko);
        i.putExtra("listBarang", listBarang);
        i.putExtra("listWishlist", listWishlist);
        i.putExtra("listRequestLelang", listRequestLelang);
        startActivity(i);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id== android.R.id.home){
            Intent i = new Intent(Login.this, Home.class);
            i.putExtra("listUser", listUser);
            i.putExtra("listToko", listToko);
            i.putExtra("listBarang", listBarang);
            i.putExtra("listWishlist", listWishlist);
            i.putExtra("listRequestLelang", listRequestLelang);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    public void login(View view) {
        stremail=email.getText().toString();
        strpass=pass.getText().toString();
        int ctr=0,ctr2=0;
        if(stremail.equals("") || strpass.equals("")){
            Toast.makeText(this, "Isi Semua Field Terlebih Dahulu ! ", Toast.LENGTH_SHORT).show();
        }
        else if(stremail.toLowerCase().equals("admin") || strpass.toLowerCase().equals("admin")){
            Intent i = new Intent(Login.this, HomeAdmin.class);
            i.putExtra("listUser", listUser);
            i.putExtra("listToko", listToko);
            i.putExtra("listBarang", listBarang);
            i.putExtra("listWishlist", listWishlist);
            i.putExtra("listRequestLelang", listRequestLelang);
            startActivity(i);
        }
        else{
            for (int i = 0; i < listUser.size(); i++) {
                if(stremail.equals(listUser.get(i).email)&& strpass.equals(listUser.get(i).password)){
                    ctr++;
                    listUser.get(i).aktif="1";
                }
            }
            if(ctr>0){
                Intent a = new Intent(Login.this, HomeRootActivity.class);
                a.putExtra("listUser", listUser);
                a.putExtra("listToko", listToko);
                a.putExtra("listBarang", listBarang);
                a.putExtra("listWishlist", listWishlist);
                a.putExtra("listRequestLelang", listRequestLelang);
                a.putExtra("adayanglogin","1");
                startActivity(a);
            }

            for (int i = 0; i < listToko.size(); i++) {
                if(stremail.equals(listToko.get(i).email)&& strpass.equals(listToko.get(i).password)){
                    listToko.get(i).aktif="1";
                    ctr2++;
                }
            }
            if(ctr2>0){
                Intent a = new Intent(Login.this, HomeToko.class);
                a.putExtra("listUser", listUser);
                a.putExtra("listToko", listToko);
                a.putExtra("listWishlist", listWishlist);
                a.putExtra("listBarang", listBarang);
                a.putExtra("listRequestLelang", listRequestLelang);
                startActivity(a);
            }
            else if(ctr==0 && ctr2==0){
                Toast.makeText(this, "Email atau Password salah", Toast.LENGTH_SHORT).show();
            }
        }
    }
}