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
import android.widget.Toast;
import java.util.ArrayList;

public class Register2 extends AppCompatActivity {
    Button register;
    EditText email,password,conpassword;
    String stremail,strpassword,strconpassword;
    ArrayList<User> listUser = new ArrayList<>();
    ArrayList<Toko> listToko = new ArrayList<>();
    ArrayList<Barang> listBarang = new ArrayList<>();
    ArrayList<ClassWishlist> listWishlist = new ArrayList<>();
    ArrayList<ClassRequestLelang> listRequestLelang = new ArrayList<>();
    String tiperegister="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        register=findViewById(R.id.btnRegister);

        email=findViewById(R.id.etEmail);
        password=findViewById(R.id.etPassword);
        conpassword=findViewById(R.id.etKonfirmasiPassword);

        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(Color.WHITE);
        drawable.setShape(GradientDrawable.OVAL);
        drawable.setStroke(5, Color.BLACK);
        register.setBackground(drawable);

        GradientDrawable drawable3 = new GradientDrawable();
        drawable3.setColor(Color.WHITE);
        drawable3.setShape(GradientDrawable.RECTANGLE);
        drawable3.setStroke(5, Color.BLACK);
        drawable3.setCornerRadius(15);
        email.setBackground(drawable3);

        GradientDrawable drawable4 = new GradientDrawable();
        drawable4.setColor(Color.WHITE);
        drawable4.setShape(GradientDrawable.RECTANGLE);
        drawable4.setStroke(5, Color.BLACK);
        drawable4.setCornerRadius(15);
        password.setBackground(drawable4);

        GradientDrawable drawable5 = new GradientDrawable();
        drawable5.setColor(Color.WHITE);
        drawable5.setShape(GradientDrawable.RECTANGLE);
        drawable5.setStroke(5, Color.BLACK);
        drawable5.setCornerRadius(15);
        conpassword.setBackground(drawable5);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        if(i.hasExtra("listUser")){
            listUser= (ArrayList<User>) i.getSerializableExtra("listUser");
            listToko= (ArrayList<Toko>) i.getSerializableExtra("listToko");
            listWishlist= (ArrayList<ClassWishlist>) i.getSerializableExtra("listWishlist");
            listBarang= (ArrayList<Barang>) i.getSerializableExtra("listBarang");
            listRequestLelang= (ArrayList<ClassRequestLelang>) i.getSerializableExtra("listRequestLelang");
            tiperegister=i.getStringExtra("tiperegister");
        }
    }

    public void register(View view) {
        stremail=email.getText().toString();
        strpassword=password.getText().toString();
        strconpassword=conpassword.getText().toString();
        int panjangpassword=strpassword.length();

        if(stremail.equals("")|| strpassword.equals("") || strconpassword.equals("")){
            Toast.makeText(this, "Isi Semua Field Terlebih Dahulu ! ", Toast.LENGTH_SHORT).show();
        }
        else if(panjangpassword<8 || panjangpassword>16){
            Toast.makeText(this, "Panjang password 8-16 karakter", Toast.LENGTH_SHORT).show();
        }
        else{
            if(!strpassword.equals(strconpassword)){
                Toast.makeText(this, "Password dan Konfirmasi Password Harus Sama ! ", Toast.LENGTH_SHORT).show();
            }
            else{
                Intent i = new Intent(Register2.this,Login.class);
                if(tiperegister.equals("toko")){
                    int ctr=0;
                    for (int j = 0; j < listToko.size(); j++) {
                        if(email.getText().toString().equals(listToko.get(j).email)){
                            ctr++;
                        }
                    }
                    if(ctr==0){
                        listToko.get(listToko.size()-1).email=stremail;
                        listToko.get(listToko.size()-1).password=strpassword;
                        i.putExtra("listUser",listUser);
                        i.putExtra("listToko",listToko);
                        i.putExtra("listWishlist", listWishlist);
                        i.putExtra("listBarang", listBarang);
                        i.putExtra("listRequestLelang", listRequestLelang);
                        startActivity(i);
                        Toast.makeText(this, "Toko berhasil didaftarkan", Toast.LENGTH_SHORT).show();
                    }
                    else if(ctr>0){
                        Toast.makeText(this, "Email sudah terpakai", Toast.LENGTH_SHORT).show();
                    }
                }
                else if(tiperegister.equals("user")){
                    int ctr=0;
                    for (int j = 0; j < listUser.size(); j++) {
                        if(email.getText().toString().equals(listUser.get(j).email)) {
                            ctr++;
                        }
                    }
                    if(ctr==0){
                        listUser.get(listUser.size()-1).email=stremail;
                        listUser.get(listUser.size()-1).password=strpassword;
                        i.putExtra("listUser",listUser);
                        i.putExtra("listToko",listToko);
                        i.putExtra("listWishlist", listWishlist);
                        i.putExtra("listBarang", listBarang);
                        i.putExtra("listRequestLelang", listRequestLelang);
                        startActivity(i);
                        Toast.makeText(this, "User berhasil didaftarkan", Toast.LENGTH_SHORT).show();
                    }
                    else if(ctr>0){
                        Toast.makeText(this, "Email sudah terpakai", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id== android.R.id.home){
            Intent i= new Intent(Register2.this,Register1.class);
            if(tiperegister.equals("toko")){
                listToko.remove(listToko.size()-1);
                i.putExtra("listUser",listUser);
                i.putExtra("listToko",listToko);
                i.putExtra("listWishlist", listWishlist);
                i.putExtra("listRequestLelang", listRequestLelang);
                i.putExtra("listBarang", listBarang);
                startActivity(i);
            }
            else if(tiperegister.equals("user")){
                listUser.remove(listUser.size()-1);
                i.putExtra("listUser",listUser);
                i.putExtra("listWishlist", listWishlist);
                i.putExtra("listRequestLelang", listRequestLelang);
                i.putExtra("listToko",listToko);
                i.putExtra("listBarang", listBarang);
                startActivity(i);
            }
        }
        return super.onOptionsItemSelected(item);
    }
}