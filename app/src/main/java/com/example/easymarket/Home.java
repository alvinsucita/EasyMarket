package com.example.easymarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    EditText search;
    RecyclerView rv;
    String userlogin;
    ArrayList<User> listUser = new ArrayList<>();
    ArrayList<Barang> listBarang = new ArrayList<>();
    ArrayList<Toko> listToko = new ArrayList<>();
    ArrayList<Barang> listBarangSearch = new ArrayList<>();
    ArrayList<Barang> listBarangFilter = new ArrayList<>();
    ArrayList<ClassWishlist> listWishlist = new ArrayList<>();
    ArrayList<ClassRequestLelang> listRequestLelang = new ArrayList<>();
    String aktif="0";
    Button filter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        listUser.add(new User("felixlonald@hotmail.com","felix lonald","lonald","Pria","Jawa Timur","20","0"));
        listUser.add(new User("sunyoto@gmail.com","sunyoto kurniawan","kurniawan","Pria","Madura","35","0"));
        listUser.add(new User("sint@gmail.com","santi","sinta","Wanita","Jakarta","22","0"));
        listToko.add(new Toko("Hypershop","Jawa Timur","hyper@gmail.com","hyperx","0"));
        listToko.add(new Toko("Happy Store","Jawa Timur","happy@gmail.com","hapstore","0"));
        listToko.add(new Toko("Games X Shop","Jawa Timur","gamesx@gmail.com","gamshop","0"));
        listBarang.add(new Barang("FA00001","Hypershop","Sendal Swallow","Sendal jepit yang sangat murah dan kualitas pas-pas an","Pakaian",11000,0,0,0,10,R.drawable.sendalswallow));
        listBarang.add(new Barang("FA00002","Hypershop","Adidas Terrex Free Hiker GTX","Sepatu yang cocok untuk pria yang aktif berpetualang","Pakaian",2800000,7,30,3,2,R.drawable.adidasterrex));
        listBarang.add(new Barang("FA00003","Hypershop","Balenciaga Triple S","Sepatu mahal yang sangat waw","Pakaian",11000000,200,500,1,10,R.drawable.balenciagatriples));
        listBarang.add(new Barang("IB00001","Happy Store","Susu Formula Enfamil A+","Merk susu pertumbuhan bayi diperkaya Prebiotik GOS tuk kesehatan pencernaan dan nutrisi penting lainnya","Ibu dan Anak",227000,5,70,30,30,R.drawable.enfamil));
        listBarang.add(new Barang("GA00001","Games X Shop","Razer Blackwidow Chroma V2","Keyboard Gaming termahal yang berkualitas bintang 5","Games",1950000,100,230,10,100,R.drawable.blackwidowchroma));
        listBarang.add(new Barang("GA00002","Games X Shop","Logitech Wireless M280 Mouse","Mouse standard yang dimiliki semua orang","Games",65000,350,1000,70,50,R.drawable.logitech));
        listBarang.add(new Barang("GA00003","Games X Shop","Razer Deathadder Mouse","Mouse Razer versi murah","Games",128000,50,120,30,0,R.drawable.deathadder));
        listRequestLelang.add(new ClassRequestLelang("FA00003"));
        search=findViewById(R.id.etSearch);
        filter=findViewById(R.id.btnFilter);
        rv=findViewById(R.id.rvhome);
        rv.setLayoutManager(new LinearLayoutManager(this));

        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(Color.WHITE);
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setStroke(8, Color.LTGRAY);
        drawable.setCornerRadius(100);
        search.setBackground(drawable);

        GradientDrawable drawable2 = new GradientDrawable();
        drawable2.setShape(GradientDrawable.RECTANGLE);
        drawable2.setCornerRadius(100);
        drawable2.setStroke(8, Color.LTGRAY);
        drawable2.setColor(Color.WHITE);
        filter.setBackground(drawable2);

        Intent i = getIntent();
        if(i.hasExtra("listUser")){
            listUser= (ArrayList<User>) i.getSerializableExtra("listUser");
            listToko= (ArrayList<Toko>) i.getSerializableExtra("listToko");
            listBarang= (ArrayList<Barang>) i.getSerializableExtra("listBarang");
            listWishlist= (ArrayList<ClassWishlist>) i.getSerializableExtra("listWishlist");
            listRequestLelang= (ArrayList<ClassRequestLelang>) i.getSerializableExtra("listRequestLelang");
            for (int j = 0; j < listUser.size(); j++) {
                if(listUser.get(j).aktif.equals("1")){
                    userlogin=listUser.get(j).nama;
                }
            }
        }
        if(i.hasExtra("adayanglogin")){
            aktif="1";
        }

        AdapterMenuBarang adapterMenuBarang= new AdapterMenuBarang(listBarang);
        rv.setAdapter(adapterMenuBarang);

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(listBarangFilter.size()>0){
                    listBarangSearch.clear();
                    for (int j = 0; j < listBarangFilter.size(); j++) {
                        if(listBarangFilter.get(j).namabarang.toLowerCase().contains(search.getText().toString().toLowerCase())){
                            listBarangSearch.add(listBarangFilter.get(j));
                        }
                    }
                    AdapterMenuBarang adapterMenuBarang= new AdapterMenuBarang(listBarangSearch);
                    rv.setAdapter(adapterMenuBarang);
                }
                else{
                    listBarangSearch.clear();
                    for (int j = 0; j < listBarang.size(); j++) {
                        if(listBarang.get(j).namabarang.toLowerCase().contains(search.getText().toString().toLowerCase())){
                            listBarangSearch.add(listBarang.get(j));
                        }
                    }
                    Toast.makeText(Home.this, "Search Woe "+listBarangSearch.size(), Toast.LENGTH_SHORT).show();
                    AdapterMenuBarang adapterMenuBarang= new AdapterMenuBarang(listBarangSearch);
                    rv.setAdapter(adapterMenuBarang);
                }
            }
        });

        adapterMenuBarang = new AdapterMenuBarang(listBarangSearch, new RVClickListener() {
            @Override
            public void recyclerViewListBarangClick(View v, int posisi) {
                if(!listBarangSearch.isEmpty()){
                    Toast.makeText(Home.this, "Masuk Searc", Toast.LENGTH_SHORT).show();
                }
                else if(!listBarangFilter.isEmpty()){
                    Toast.makeText(Home.this, "MasukFilter", Toast.LENGTH_SHORT).show();
                }
                else{
                    int indeks = posisi;
                    Intent i = new Intent(Home.this,InfoBarang.class);
                    putextra(i);
                    i.putExtra("indeks",indeks);
                    startActivity(i);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu,menu);
        if(aktif.equals("0")){
            menu.getItem(0).setVisible(true);
            menu.getItem(1).setVisible(false);
            menu.getItem(2).setVisible(false);
            menu.getItem(3).setVisible(false);
            menu.getItem(4).setVisible(false);
            menu.getItem(5).setVisible(false);
            menu.getItem(6).setVisible(false);
            menu.getItem(7).setVisible(false);
            menu.getItem(8).setVisible(false);
        }
        else{
            menu.getItem(3).setTitle("Hai, "+userlogin+" !");
            menu.getItem(3).setVisible(true);
            menu.getItem(0).setVisible(false);
            menu.getItem(1).setVisible(true);
            menu.getItem(2).setVisible(true);
            menu.getItem(4).setVisible(true);
            menu.getItem(5).setVisible(true);
            menu.getItem(6).setVisible(true);
            menu.getItem(7).setVisible(false);
            menu.getItem(8).setVisible(true);
        }
        return super.onCreateOptionsMenu(menu);
    }

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.popupmenu, popup.getMenu());
        popup.show();

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId()==R.id.itemPakaian){
                    if(listBarangSearch.size()>0){
                        listBarangFilter.clear();
                        for (int j = 0; j < listBarangSearch.size(); j++) {
                            if(listBarangSearch.get(j).kategori.toLowerCase().contains("pakaian")){
                                listBarangFilter.add(listBarangSearch.get(j));
                            }
                        }
                        AdapterMenuBarang adapterMenuBarang= new AdapterMenuBarang(listBarangFilter);
                        rv.setAdapter(adapterMenuBarang);
                    }
                    else{
                        listBarangFilter.clear();
                        for (int j = 0; j < listBarang.size(); j++) {
                            if(listBarang.get(j).kategori.toLowerCase().contains("pakaian")){
                                listBarangFilter.add(listBarang.get(j));
                            }
                        }
                        AdapterMenuBarang adapterMenuBarang= new AdapterMenuBarang(listBarangFilter);
                        rv.setAdapter(adapterMenuBarang);
                    }
                }
                else if(item.getItemId()==R.id.itemIbuAnak){
                    if(listBarangSearch.size()>0){
                        listBarangFilter.clear();
                        for (int j = 0; j < listBarangSearch.size(); j++) {
                            if(listBarangSearch.get(j).kategori.toLowerCase().contains("ibu")){
                                listBarangFilter.add(listBarangSearch.get(j));
                            }
                        }
                        AdapterMenuBarang adapterMenuBarang= new AdapterMenuBarang(listBarangFilter);
                        rv.setAdapter(adapterMenuBarang);
                    }
                    else{
                        listBarangFilter.clear();
                        for (int j = 0; j < listBarang.size(); j++) {
                            if(listBarang.get(j).kategori.toLowerCase().contains("ibu")){
                                listBarangFilter.add(listBarang.get(j));
                            }
                        }
                        AdapterMenuBarang adapterMenuBarang= new AdapterMenuBarang(listBarangFilter);
                        rv.setAdapter(adapterMenuBarang);
                    }
                }
                else if(item.getItemId()==R.id.itemElektronik){
                    if(listBarangSearch.size()>0){
                        listBarangFilter.clear();
                        for (int j = 0; j < listBarangSearch.size(); j++) {
                            if(listBarangSearch.get(j).kategori.toLowerCase().contains("elektronik")){
                                listBarangFilter.add(listBarangSearch.get(j));
                            }
                        }
                        AdapterMenuBarang adapterMenuBarang= new AdapterMenuBarang(listBarangFilter);
                        rv.setAdapter(adapterMenuBarang);
                    }
                    else{
                        listBarangFilter.clear();
                        for (int j = 0; j < listBarang.size(); j++) {
                            if(listBarang.get(j).kategori.toLowerCase().contains("elektronik")){
                                listBarangFilter.add(listBarang.get(j));
                            }
                        }
                        AdapterMenuBarang adapterMenuBarang= new AdapterMenuBarang(listBarangFilter);
                        rv.setAdapter(adapterMenuBarang);
                    }
                }
                else if(item.getItemId()==R.id.itemMakananMinuman){
                    if(listBarangSearch.size()>0){
                        listBarangFilter.clear();
                        for (int j = 0; j < listBarangSearch.size(); j++) {
                            if(listBarangSearch.get(j).kategori.toLowerCase().contains("makan")){
                                listBarangFilter.add(listBarangSearch.get(j));
                            }
                        }
                        AdapterMenuBarang adapterMenuBarang= new AdapterMenuBarang(listBarangFilter);
                        rv.setAdapter(adapterMenuBarang);
                    }
                    else{
                        listBarangFilter.clear();
                        for (int j = 0; j < listBarang.size(); j++) {
                            if(listBarang.get(j).kategori.toLowerCase().contains("makan")){
                                listBarangFilter.add(listBarang.get(j));
                            }
                        }
                        AdapterMenuBarang adapterMenuBarang= new AdapterMenuBarang(listBarangFilter);
                        rv.setAdapter(adapterMenuBarang);
                    }
                }
                else if(item.getItemId()==R.id.itemGames){
                    if(listBarangSearch.size()>0){
                        listBarangFilter.clear();
                        for (int j = 0; j < listBarangSearch.size(); j++) {
                            if(listBarangSearch.get(j).kategori.toLowerCase().contains("games")){
                                listBarangFilter.add(listBarangSearch.get(j));
                            }
                        }
                        AdapterMenuBarang adapterMenuBarang= new AdapterMenuBarang(listBarangFilter);
                        rv.setAdapter(adapterMenuBarang);
                    }
                    else{
                        listBarangFilter.clear();
                        for (int j = 0; j < listBarang.size(); j++) {
                            if(listBarang.get(j).kategori.toLowerCase().contains("games")){
                                listBarangFilter.add(listBarang.get(j));
                            }
                        }
                        AdapterMenuBarang adapterMenuBarang= new AdapterMenuBarang(listBarangFilter);
                        rv.setAdapter(adapterMenuBarang);
                    }
                }
                else if(item.getItemId()==R.id.itemKecantikan){
                    if(listBarangSearch.size()>0){
                        listBarangFilter.clear();
                        for (int j = 0; j < listBarangSearch.size(); j++) {
                            if(listBarangSearch.get(j).kategori.toLowerCase().contains("kecantikan")){
                                listBarangFilter.add(listBarangSearch.get(j));
                            }
                        }
                        AdapterMenuBarang adapterMenuBarang= new AdapterMenuBarang(listBarangFilter);
                        rv.setAdapter(adapterMenuBarang);
                    }
                    else{
                        listBarangFilter.clear();
                        for (int j = 0; j < listBarang.size(); j++) {
                            if(listBarang.get(j).kategori.toLowerCase().contains("kecantikan")){
                                listBarangFilter.add(listBarang.get(j));
                            }
                        }
                        AdapterMenuBarang adapterMenuBarang= new AdapterMenuBarang(listBarangFilter);
                        rv.setAdapter(adapterMenuBarang);
                    }
                }
                else if(item.getItemId()==R.id.itemOlahraga){
                    if(listBarangSearch.size()>0){
                        listBarangFilter.clear();
                        for (int j = 0; j < listBarangSearch.size(); j++) {
                            if(listBarangSearch.get(j).kategori.toLowerCase().contains("olahraga")){
                                listBarangFilter.add(listBarangSearch.get(j));
                            }
                        }
                        AdapterMenuBarang adapterMenuBarang= new AdapterMenuBarang(listBarangFilter);
                        rv.setAdapter(adapterMenuBarang);
                    }
                    else{
                        listBarangFilter.clear();
                        for (int j = 0; j < listBarang.size(); j++) {
                            if(listBarang.get(j).kategori.toLowerCase().contains("olahraga")){
                                listBarangFilter.add(listBarang.get(j));
                            }
                        }
                        AdapterMenuBarang adapterMenuBarang= new AdapterMenuBarang(listBarangFilter);
                        rv.setAdapter(adapterMenuBarang);
                    }
                }
                else if(item.getItemId()==R.id.itemLainlain){
                    if(listBarangSearch.size()>0){
                        listBarangFilter.clear();
                        for (int j = 0; j < listBarangSearch.size(); j++) {
                            if(listBarangSearch.get(j).kategori.toLowerCase().contains("lain")){
                                listBarangFilter.add(listBarangSearch.get(j));
                            }
                        }
                        AdapterMenuBarang adapterMenuBarang= new AdapterMenuBarang(listBarangFilter);
                        rv.setAdapter(adapterMenuBarang);
                    }
                    else{
                        listBarangFilter.clear();
                        for (int j = 0; j < listBarang.size(); j++) {
                            if(listBarang.get(j).kategori.toLowerCase().contains("lain")){
                                listBarangFilter.add(listBarang.get(j));
                            }
                        }
                        AdapterMenuBarang adapterMenuBarang= new AdapterMenuBarang(listBarangFilter);
                        rv.setAdapter(adapterMenuBarang);
                    }
                }
                else if(item.getItemId()==R.id.itemReset){
                    AdapterMenuBarang adapterMenuBarang= new AdapterMenuBarang(listBarang);
                    rv.setAdapter(adapterMenuBarang);

                    listBarangFilter.clear();
                    listBarangSearch.clear();
                    search.setText("");
                }
                return true;
            }
        });
    }



    public Intent putextra(Intent i){
        i.putExtra("listUser", listUser);
        i.putExtra("listWishlist", listWishlist);
        i.putExtra("listToko", listToko);
        i.putExtra("listBarang", listBarang);
        i.putExtra("listRequestLelang", listRequestLelang);
        return i;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.itemLogin){
            Intent i = new Intent(Home.this,Login.class);
            i = putextra(i);
            startActivity(i);
        }
        else if(item.getItemId()==R.id.itemLogout){
            Intent i = new Intent(Home.this,Home.class);
            for (int j = 0; j < listUser.size(); j++) {
                if(listUser.get(j).aktif.equals("1")){
                    listUser.get(j).aktif="0";
                    i = putextra(i);
                    startActivity(i);
                }
            }
        }
        else if(item.getItemId()==R.id.itemEvent){
            Intent i = new Intent(Home.this,EventPage.class);
            i = putextra(i);
            startActivity(i);
        }
        else if(item.getItemId() == R.id.itemNama){
            Intent i = new Intent(Home.this,ProfileUtamaActivity.class);
            i = putextra(i);
            startActivity(i);
        }
        else if(item.getItemId()==R.id.itemWishlist){
            Intent i = new Intent(Home.this,WishList.class);
            i = putextra(i);
            i.putExtra("adayanglogin",aktif);
            startActivity(i);
        }
        else if(item.getItemId()==R.id.itemLelang){
            Intent i = new Intent(Home.this,LelangActivity.class);
            i = putextra(i);
            startActivity(i);
        }
        else if(item.getItemId()==R.id.itemCekPengiriman){
            Intent i = new Intent(Home.this,CekPerjalananActivity.class);
            startActivity(i);
        }
        else if(item.getItemId()==R.id.itemRefund){
            Intent i = new Intent(Home.this,RefundActivity.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
}