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
    RadioGroup gendergroup,registergroup;
    RadioButton pria,wanita,toko,user;
    EditText nama;
    ArrayList<String> spinnerArray =  new ArrayList<>();
    ArrayList<String> spinnerArray2 =  new ArrayList<>();
    ArrayList<User> listUser = new ArrayList<>();
    ArrayList<Barang> listBarang = new ArrayList<>();
    ArrayList<ClassWishlist> listWishlist = new ArrayList<>();
    ArrayList<Toko> listToko = new ArrayList<>();
    String tiperegister="";
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
        registergroup = findViewById(R.id.jenisuser);
        gendergroup = findViewById(R.id.gender);

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

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner sItems = (Spinner) findViewById(R.id.spUmur);
        sItems.setAdapter(adapter);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spinnerArray2);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner sItems2 = (Spinner) findViewById(R.id.spDaerahAsal);
        sItems2.setAdapter(adapter2);

        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setStroke(5, Color.BLACK);
        drawable.setCornerRadius(15);
        drawable.setColor(Color.WHITE);
        nama.setBackground(drawable);

        GradientDrawable drawable2 = new GradientDrawable();
        drawable2.setShape(GradientDrawable.RECTANGLE);
        drawable2.setStroke(5, Color.BLACK);
        drawable2.setCornerRadius(15);
        drawable2.setColor(Color.WHITE);
        umur.setBackground(drawable2);

        GradientDrawable drawable3 = new GradientDrawable();
        drawable3.setShape(GradientDrawable.RECTANGLE);
        drawable3.setStroke(5, Color.BLACK);
        drawable3.setCornerRadius(15);
        drawable3.setColor(Color.WHITE);
        gendergroup.setBackground(drawable3);

        GradientDrawable drawable5 = new GradientDrawable();
        drawable5.setShape(GradientDrawable.OVAL);
        drawable5.setStroke(5, Color.BLACK);
        drawable5.setCornerRadius(15);
        drawable5.setColor(Color.WHITE);
        next.setBackground(drawable5);

        GradientDrawable drawable6 = new GradientDrawable();
        drawable6.setShape(GradientDrawable.RECTANGLE);
        drawable6.setStroke(5, Color.BLACK);
        drawable6.setCornerRadius(15);
        drawable6.setColor(Color.WHITE);
        daerahasal.setBackground(drawable6);

        GradientDrawable drawable7 = new GradientDrawable();
        drawable7.setShape(GradientDrawable.RECTANGLE);
        drawable7.setStroke(5, Color.BLACK);
        drawable7.setCornerRadius(15);
        drawable7.setColor(Color.WHITE);
        registergroup.setBackground(drawable7);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        if(i.hasExtra("listUser")){
            listUser= (ArrayList<User>) i.getSerializableExtra("listUser");
            listToko= (ArrayList<Toko>) i.getSerializableExtra("listToko");
            listWishlist= (ArrayList<ClassWishlist>) i.getSerializableExtra("listWishlist");
            listBarang= (ArrayList<Barang>) i.getSerializableExtra("listBarang");
        }
    }

    public void toRegister2(View view) {
        if(nama.getText().toString().equals("") || user.isChecked()==false && toko.isChecked()==false ){
            Toast.makeText(this, "Isi Semua Field Terlebih Dahulu ! ", Toast.LENGTH_LONG).show();
        }
        else {
            if(tiperegister.equals("user")) {
                if (pria.isChecked()) {
                    String strnama= nama.getText().toString();
                    String strdaerah = daerahasal.getSelectedItem().toString();
                    String strumur  = umur.getSelectedItem().toString();

                    if(listUser.size()==0){
                        listUser.add(new User("", strnama, "","Pria" ,strdaerah,strumur,"0"));
                        Intent a = new Intent(Register1.this, Register2.class);
                        a.putExtra("listUser", listUser);
                        a.putExtra("listWishlist", listWishlist);
                        a.putExtra("tiperegister",tiperegister);
                        a.putExtra("listToko", listToko);
                        a.putExtra("listBarang", listBarang);
                        startActivity(a);
                    }
                    else{
                        for (int i = 0; i < listUser.size(); i++) {
                            if(!nama.getText().toString().equals(listUser.get(i).nama) && i==listUser.size()-1){
                                listUser.add(new User("", strnama, "","Pria" ,strdaerah,strumur,"0"));
                                Intent a = new Intent(Register1.this, Register2.class);
                                a.putExtra("listUser", listUser);
                                a.putExtra("tiperegister",tiperegister);
                                a.putExtra("listWishlist", listWishlist);
                                a.putExtra("listToko", listToko);
                                a.putExtra("listBarang", listBarang);
                                startActivity(a);
                                break;
                            }
                            else if(nama.getText().toString().equals(listUser.get(i).nama)) {
                                Toast.makeText(this, "Nama sudah terpakai", Toast.LENGTH_SHORT).show();
                                break;
                            }
                        }
                    }
                }
                else if (wanita.isChecked()) {
                    String strnama= nama.getText().toString();
                    String strdaerah = daerahasal.getSelectedItem().toString();
                    String strumur  = umur.getSelectedItem().toString();

                    if(listUser.size()==0){
                        listUser.add(new User("", strnama, "","Wanita" ,strdaerah,strumur,"0"));
                        Intent a = new Intent(Register1.this, Register2.class);
                        a.putExtra("listUser", listUser);
                        a.putExtra("tiperegister",tiperegister);
                        a.putExtra("listToko", listToko);
                        a.putExtra("listWishlist", listWishlist);
                        a.putExtra("listBarang", listBarang);
                        startActivity(a);
                    }
                    else{
                        for (int i = 0; i < listUser.size(); i++) {
                            if(nama.getText().toString().equals(listUser.get(i).nama)) {
                                Toast.makeText(this, "Nama sudah terpakai", Toast.LENGTH_SHORT).show();
                                break;
                            }
                            else if(!nama.getText().toString().equals(listUser.get(i).nama) && i==listUser.size()-1){
                                listUser.add(new User("", strnama, "","Wanita" ,strdaerah,strumur,"0"));
                                Intent a = new Intent(Register1.this, Register2.class);
                                a.putExtra("listUser", listUser);
                                a.putExtra("listWishlist", listWishlist);
                                a.putExtra("tiperegister",tiperegister);
                                a.putExtra("listToko", listToko);
                                a.putExtra("listBarang", listBarang);
                                startActivity(a);
                                break;
                            }
                        }
                    }
                }
                else {
                    Toast.makeText(this, "Isi Semua Field Terlebih Dahulu ! ", Toast.LENGTH_LONG).show();
                }
            }
            else if(tiperegister.equals("toko")){
                String strnama= nama.getText().toString();
                String strdaerah = daerahasal.getSelectedItem().toString();

                if(listToko.size()==0){
                    listToko.add(new Toko(strnama,strdaerah,"","","0"));
                    Intent a = new Intent(Register1.this, Register2.class);
                    a.putExtra("listUser", listUser);
                    a.putExtra("listBarang", listBarang);
                    a.putExtra("listWishlist", listWishlist);
                    a.putExtra("tiperegister",tiperegister);
                    a.putExtra("listToko", listToko);
                    startActivity(a);
                }
                else{
                    for (int i = 0; i < listToko.size(); i++) {
                        if(nama.getText().toString().equals(listToko.get(i).nama)) {
                            Toast.makeText(this, "Nama sudah terpakai", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        else if(!nama.getText().toString().equals(listToko.get(i).nama) && i==listToko.size()-1){
                            listToko.add(new Toko(strnama,strdaerah,"","","0"));
                            Intent a = new Intent(Register1.this, Register2.class);
                            a.putExtra("listUser", listUser);
                            a.putExtra("listBarang", listBarang);
                            a.putExtra("tiperegister",tiperegister);
                            a.putExtra("listWishlist", listWishlist);
                            a.putExtra("listToko", listToko);
                            startActivity(a);
                            break;
                        }
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
    }

    public void pilihuser(View view) {
        umur.setEnabled(true);
        umur.setClickable(true);
        pria.setEnabled(true);
        pria.setClickable(true);
        wanita.setEnabled(true);
        wanita.setClickable(true);
        tiperegister="user";
    }
}