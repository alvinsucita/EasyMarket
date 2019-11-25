package com.example.easymarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Register1 extends AppCompatActivity {

    Button next;
    Spinner umur,daerahasal;
    RadioButton pria,wanita,toko,user;
    EditText nama;
    ArrayList<String> spinnerArray =  new ArrayList<>();
    ArrayList<String> spinnerArray2 =  new ArrayList<>();
    ArrayList<User> listUser = new ArrayList<>();
    ArrayList<Barang> listBarang = new ArrayList<>();
    ArrayList<ClassWishlist> listWishlist = new ArrayList<>();
    ArrayList<Toko> listToko = new ArrayList<>();
    ArrayList<ClassRequestLelang> listRequestLelang = new ArrayList<>();
    String tiperegister="";
    RadioGroup rgJenis,rgGender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register1);
        next = findViewById(R.id.btnNextPage);
        umur = findViewById(R.id.spUmur);
        daerahasal = findViewById(R.id.spDaerahAsal);
        pria = findViewById(R.id.rbPria);
        wanita = findViewById(R.id.rbWanita);
        toko=findViewById(R.id.rbToko);
        user=findViewById(R.id.rbUser);
        nama = findViewById(R.id.etNama);
        rgJenis=findViewById(R.id.jenisuser);
        rgGender=findViewById(R.id.gender);

        for (int i = 18; i < 61; i++) {
            spinnerArray.add(i + "");
        }

        spinnerArray2.add("Jakarta");
        spinnerArray2.add("Jawa Barat");
        spinnerArray2.add("Jawa Tengah");
        spinnerArray2.add("Jawa Timur");
        spinnerArray2.add("Yogyakarta");
        spinnerArray2.add("Madura");
        spinnerArray2.add("Banten");

        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setCornerRadius(100);
        drawable.setColor(Color.WHITE);
        rgJenis.setBackground(drawable);

        GradientDrawable drawable2 = new GradientDrawable();
        drawable2.setShape(GradientDrawable.RECTANGLE);
        drawable2.setCornerRadius(100);
        drawable2.setColor(Color.WHITE);
        nama.setBackground(drawable2);

        GradientDrawable drawable3 = new GradientDrawable();
        drawable3.setShape(GradientDrawable.RECTANGLE);
        drawable3.setCornerRadius(100);
        drawable3.setColor(Color.WHITE);
        umur.setBackground(drawable3);

        GradientDrawable drawable4 = new GradientDrawable();
        drawable4.setShape(GradientDrawable.RECTANGLE);
        drawable4.setCornerRadius(100);
        drawable4.setColor(Color.WHITE);
        rgGender.setBackground(drawable4);

        GradientDrawable drawable5 = new GradientDrawable();
        drawable5.setShape(GradientDrawable.RECTANGLE);
        drawable5.setCornerRadius(100);
        drawable5.setColor(Color.WHITE);
        daerahasal.setBackground(drawable5);

        GradientDrawable drawable6 = new GradientDrawable();
        drawable6.setShape(GradientDrawable.RECTANGLE);
        drawable6.setCornerRadius(100);
        drawable6.setColor(Color.rgb(255,136,0));
        next.setBackground(drawable6);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner sItems = (Spinner) findViewById(R.id.spUmur);
        sItems.setAdapter(adapter);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spinnerArray2);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner sItems2 = (Spinner) findViewById(R.id.spDaerahAsal);
        sItems2.setAdapter(adapter2);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        if(i.hasExtra("listUser")){
            listUser= (ArrayList<User>) i.getSerializableExtra("listUser");
            listToko= (ArrayList<Toko>) i.getSerializableExtra("listToko");
            listWishlist= (ArrayList<ClassWishlist>) i.getSerializableExtra("listWishlist");
            listRequestLelang= (ArrayList<ClassRequestLelang>) i.getSerializableExtra("listRequestLelang");
            listBarang= (ArrayList<Barang>) i.getSerializableExtra("listBarang");
        }

        umur.setEnabled(false);
        umur.setClickable(false);
        pria.setEnabled(false);
        pria.setClickable(false);
        wanita.setEnabled(false);
        wanita.setClickable(false);
        nama.setEnabled(false);
        nama.setClickable(false);
        daerahasal.setEnabled(false);
        daerahasal.setClickable(false);
    }

    public void toRegister2(View view) {
        String strnama= nama.getText().toString();
        String strdaerah = daerahasal.getSelectedItem().toString();
        String strumur  = umur.getSelectedItem().toString();
        String strgender = "";

        if(nama.getText().toString().equals("") || user.isChecked()==false && toko.isChecked()==false){
            Toast.makeText(this, "Isi Semua Field Terlebih Dahulu ! ", Toast.LENGTH_LONG).show();
        }
        else {
            if(pria.isChecked()){
                strgender="Pria";
            }
            else if(wanita.isChecked()){
                strgender="Wanita";
            }

            if(tiperegister.equals("user")) {
                int ctr=0;

                if(wanita.isChecked()==false && pria.isChecked()==false){
                    Toast.makeText(this, "Isi Semua Field Terlebih Dahulu ! ", Toast.LENGTH_LONG).show();
                }
                else{
                    if(listUser.size()==0){
                        listUser.add(new User("", strnama, "","Pria" ,strdaerah,strumur,"0"));
                        Intent a = new Intent(Register1.this, Register2.class);
                        a.putExtra("listUser", listUser);
                        a.putExtra("listWishlist", listWishlist);
                        a.putExtra("tiperegister",tiperegister);
                        a.putExtra("listRequestLelang", listRequestLelang);
                        a.putExtra("listToko", listToko);
                        a.putExtra("listBarang", listBarang);
                        startActivity(a);
                    }
                    else{
                        for (int i = 0; i < listUser.size(); i++) {
                            if(nama.getText().toString().equals(listUser.get(i).nama)) {
                                ctr++;
                            }
                        }
                        if(ctr==0){
                            listUser.add(new User("", strnama, "","Pria" ,strdaerah,strumur,"0"));
                            Intent a = new Intent(Register1.this, Register2.class);
                            a.putExtra("listUser", listUser);
                            a.putExtra("tiperegister",tiperegister);
                            a.putExtra("listWishlist", listWishlist);
                            a.putExtra("listRequestLelang", listRequestLelang);
                            a.putExtra("listToko", listToko);
                            a.putExtra("listBarang", listBarang);
                            startActivity(a);
                        }
                        else if(ctr>0){
                            Toast.makeText(this, "Nama sudah terpakai", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
            else if(tiperegister.equals("toko")){
                int ctr=0;

                if(listToko.size()==0){
                    listToko.add(new Toko(strnama,strdaerah,"","","0"));
                    Intent a = new Intent(Register1.this, Register2.class);
                    a.putExtra("listUser", listUser);
                    a.putExtra("listBarang", listBarang);
                    a.putExtra("listWishlist", listWishlist);
                    a.putExtra("listRequestLelang", listRequestLelang);
                    a.putExtra("tiperegister",tiperegister);
                    a.putExtra("listToko", listToko);
                    startActivity(a);
                }
                else{
                    for (int i = 0; i < listToko.size(); i++) {
                        if(nama.getText().toString().equals(listToko.get(i).nama)) {
                            ctr++;
                        }
                    }
                    if(ctr==0){
                        listToko.add(new Toko(strnama,strdaerah,"","","0"));
                        Intent a = new Intent(Register1.this, Register2.class);
                        a.putExtra("listUser", listUser);
                        a.putExtra("listBarang", listBarang);
                        a.putExtra("tiperegister",tiperegister);
                        a.putExtra("listRequestLelang", listRequestLelang);
                        a.putExtra("listWishlist", listWishlist);
                        a.putExtra("listToko", listToko);
                        startActivity(a);
                    }
                    else if(ctr>0){
                        Toast.makeText(this, "Nama sudah terpakai", Toast.LENGTH_SHORT).show();
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
        umur.setEnabled(false);
        umur.setClickable(false);
        pria.setEnabled(false);
        pria.setClickable(false);
        wanita.setEnabled(false);
        wanita.setClickable(false);
        nama.setEnabled(true);
        nama.setClickable(true);
        daerahasal.setEnabled(true);
        daerahasal.setClickable(true);
    }

    public void pilihuser(View view) {
        tiperegister="user";
        umur.setEnabled(true);
        umur.setClickable(true);
        pria.setEnabled(true);
        pria.setClickable(true);
        wanita.setEnabled(true);
        wanita.setClickable(true);
        nama.setEnabled(true);
        nama.setClickable(true);
        daerahasal.setEnabled(true);
        daerahasal.setClickable(true);
    }
}