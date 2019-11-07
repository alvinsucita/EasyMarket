package com.example.easymarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Login extends AppCompatActivity {
    TextView toregis;
    EditText email,pass;
    Button login;
    String stremail,strpass;
    ArrayList<User> listUser = new ArrayList<>();
    ArrayList<Toko> listToko = new ArrayList<>();
    ArrayList<Barang> listBarang = new ArrayList<>();
    ArrayList<ClassWishlist> listWishlist = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=findViewById(R.id.etEmail);
        toregis=findViewById(R.id.tvToRegis);
        pass=findViewById(R.id.etPassword);
        login=findViewById(R.id.btnLogin);

        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(Color.WHITE);
        drawable.setShape(GradientDrawable.OVAL);
        drawable.setStroke(5, Color.BLACK);
        login.setBackground(drawable);

        GradientDrawable drawable2 = new GradientDrawable();
        drawable2.setColor(Color.WHITE);
        drawable2.setShape(GradientDrawable.RECTANGLE);
        drawable2.setStroke(5, Color.BLACK);
        drawable2.setCornerRadius(15);
        email.setBackground(drawable2);

        GradientDrawable drawable3 = new GradientDrawable();
        drawable3.setColor(Color.WHITE);
        drawable3.setShape(GradientDrawable.RECTANGLE);
        drawable3.setStroke(5, Color.BLACK);
        drawable3.setCornerRadius(15);
        pass.setBackground(drawable3);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        if(i.hasExtra("listUser")){
            listUser= (ArrayList<User>) i.getSerializableExtra("listUser");
            listToko= (ArrayList<Toko>) i.getSerializableExtra("listToko");
            listBarang= (ArrayList<Barang>) i.getSerializableExtra("listBarang");
            listWishlist= (ArrayList<ClassWishlist>) i.getSerializableExtra("listWishlist");
        }
    }

    public void toRegis(View view) {
        Intent i = new Intent(Login.this, Register1.class);
        i.putExtra("listUser", listUser);
        i.putExtra("listToko", listToko);
        i.putExtra("listBarang", listBarang);
        i.putExtra("listWishlist", listWishlist);
        startActivity(i);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id== android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void login(View view) {
        stremail=email.getText().toString();
        strpass=pass.getText().toString();
        if(stremail.equals("") || strpass.equals("")){
            Toast.makeText(this, "Isi Semua Field Terlebih Dahulu ! ", Toast.LENGTH_SHORT).show();
        }
        else if(stremail.equals("admin") || strpass.equals("admin")){
            Intent i = new Intent(Login.this, HomeAdmin.class);
            i.putExtra("listUser", listUser);
            i.putExtra("listToko", listToko);
            i.putExtra("listBarang", listBarang);
            i.putExtra("listWishlist", listWishlist);
            startActivity(i);
        }
        else{
            for (int i = 0; i < listUser.size(); i++) {
                if(stremail.equals(listUser.get(i).email)&& strpass.equals(listUser.get(i).password)){
                    listUser.get(i).aktif="1";
                    Intent a = new Intent(Login.this, Home.class);
                    a.putExtra("listUser", listUser);
                    a.putExtra("listToko", listToko);
                    a.putExtra("listBarang", listBarang);
                    a.putExtra("listWishlist", listWishlist);
                    a.putExtra("adayanglogin","1");
                    startActivity(a);
                    break;
                }
            }
            for (int i = 0; i < listToko.size(); i++) {
                if(stremail.equals(listToko.get(i).email)&& strpass.equals(listToko.get(i).password)){
                    listToko.get(i).aktif="1";
                    Intent a = new Intent(Login.this, HomeToko.class);
                    a.putExtra("listUser", listUser);
                    a.putExtra("listToko", listToko);
                    a.putExtra("listWishlist", listWishlist);
                    a.putExtra("listBarang", listBarang);
                    a.putExtra("yanglogin",listToko.get(i).nama);
                    startActivity(a);
                    break;
                }
            }
        }
    }
}