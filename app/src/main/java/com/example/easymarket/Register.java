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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import java.util.ArrayList;

public class Register extends AppCompatActivity {
    Button register;
    EditText email,password,conpassword;
    String stremail,strpassword,strconpassword;
    ArrayList<User> listUser = new ArrayList<>();
    ArrayList<Toko> listToko = new ArrayList<>();
    ArrayList<Barang> listBarang = new ArrayList<>();
    ArrayList<ClassWishlist> listWishlist = new ArrayList<>();
    RadioButton toko,user;
    RadioGroup rgJenis;
    ArrayList<ClassRequestLelang> listRequestLelang = new ArrayList<>();
    String tiperegister="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register=findViewById(R.id.btnRegister);
        email=findViewById(R.id.etEmail);
        password=findViewById(R.id.etPassword);
        conpassword=findViewById(R.id.etKonfirmasiPassword);
        toko=findViewById(R.id.rbToko);
        user=findViewById(R.id.rbUser);
        rgJenis=findViewById(R.id.jenisuser);

        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setCornerRadius(100);
        drawable.setColor(Color.BLACK);
        register.setBackground(drawable);

        GradientDrawable drawable3 = new GradientDrawable();
        drawable3.setShape(GradientDrawable.RECTANGLE);
        drawable3.setCornerRadius(100);
        drawable3.setColor(Color.WHITE);
        password.setBackground(drawable3);

        GradientDrawable drawable4 = new GradientDrawable();
        drawable4.setShape(GradientDrawable.RECTANGLE);
        drawable4.setCornerRadius(100);
        drawable4.setColor(Color.WHITE);
        email.setBackground(drawable4);

        GradientDrawable drawable7 = new GradientDrawable();
        drawable7.setShape(GradientDrawable.RECTANGLE);
        drawable7.setCornerRadius(100);
        drawable7.setColor(Color.WHITE);
        rgJenis.setBackground(drawable7);

        GradientDrawable drawable5 = new GradientDrawable();
        drawable5.setShape(GradientDrawable.RECTANGLE);
        drawable5.setCornerRadius(100);
        drawable5.setColor(Color.WHITE);
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
        }
    }

    public void register(View view) {
        stremail=email.getText().toString();
        strpassword=password.getText().toString();
        strconpassword=conpassword.getText().toString();
        int panjangpassword=strpassword.length();

        if(stremail.equals("")|| strpassword.equals("") || strconpassword.equals("")||tiperegister.equals("")){
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
                Intent i = new Intent(Register.this,Login.class);
                if(tiperegister.equals("toko")){
                    int ctr=0;
                    for (int j = 0; j < listToko.size(); j++) {
                        if(email.getText().toString().equals(listToko.get(j).email)){
                            ctr++;
                        }
                    }
                    if(ctr==0){
                        listToko.add(new Toko(stremail,"",stremail,strpassword,"0"));
                        Intent a = new Intent(Register.this, Login.class);
                        a.putExtra("listUser", listUser);
                        a.putExtra("listBarang", listBarang);
                        a.putExtra("listWishlist", listWishlist);
                        a.putExtra("listRequestLelang", listRequestLelang);
                        a.putExtra("listToko", listToko);
                        startActivity(a);
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
                        listUser.add(new User(stremail,stremail,strpassword,"","","","0"));
                        Intent a = new Intent(Register.this, Login.class);
                        a.putExtra("listUser", listUser);
                        a.putExtra("listBarang", listBarang);
                        a.putExtra("listWishlist", listWishlist);
                        a.putExtra("listRequestLelang", listRequestLelang);
                        a.putExtra("listToko", listToko);
                        startActivity(a);
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
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void pilihtoko(View view) {
        tiperegister="toko";
    }

    public void pilihuser(View view) {
        tiperegister="user";
    }

    public void toLogin(View view) {
        Intent i = new Intent(Register.this, Login.class);
        i.putExtra("listUser", listUser);
        i.putExtra("listToko", listToko);
        i.putExtra("listBarang", listBarang);
        i.putExtra("listWishlist", listWishlist);
        i.putExtra("listRequestLelang", listRequestLelang);
        startActivity(i);
    }
}