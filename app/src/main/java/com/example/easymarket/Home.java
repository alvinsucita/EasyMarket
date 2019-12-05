package com.example.easymarket;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Home extends AppCompatActivity {

    EditText search;
    RecyclerView rv;
    String userlogin;
    BottomNavigationView bottomNavHome;
    ArrayList<User> listUser = new ArrayList<>();
    ArrayList<Barang> listBarang = new ArrayList<>();
    ArrayList<Toko> listToko = new ArrayList<>();
    ArrayList<Barang> listBarangSearch = new ArrayList<>();
    ArrayList<Barang> listBarangFilter = new ArrayList<>();
    ArrayList<ClassWishlist> listWishlist = new ArrayList<>();
    ArrayList<ClassRequestLelang> listRequestLelang = new ArrayList<>();
    String aktif="0";
    Button filter;
    Boolean adafilter=false;
    Boolean adasearch=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        listUser.add(new User("felixlonald@hotmail.com","felix lonald","lonald","Pria","Jawa Timur","20"));
        listUser.add(new User("sunyoto@gmail.com","sunyoto kurniawan","kurniawan","Pria","Madura","35"));
        listUser.add(new User("sint@gmail.com","santi","sinta","Wanita","Jakarta","22"));
        listToko.add(new Toko("Hypershop","Jawa Timur","hyper@gmail.com","hyperx"));
        listToko.add(new Toko("Happy Store","Jawa Timur","happy@gmail.com","hapstore"));
        listToko.add(new Toko("Games X Shop","Jawa Timur","gamesx@gmail.com","gamshop"));
//        listBarang.add(new Barang("FA00001","Hypershop","Sendal Swallow","Sendal jepit yang sangat murah dan kualitas pas-pas an","Pakaian",11000,0,0,0,10,R.drawable.sendalswallow,R.drawable.swallow2,R.drawable.sandalswallow3));
//        listBarang.add(new Barang("FA00002","Hypershop","Adidas Terrex Free Hiker GTX","Sepatu yang cocok untuk pria yang aktif berpetualang","Pakaian",2800000,7,30,3,2,R.drawable.adidasterrex,R.drawable.adidasterrex2,R.drawable.terrex3));
//        listBarang.add(new Barang("FA00003","Hypershop","Balenciaga Triple S","Sepatu mahal yang sangat waw","Pakaian",11000000,200,500,1,10,R.drawable.balenciagatriples,R.drawable.balenciaga2,R.drawable.balenciaga3));
//        listBarang.add(new Barang("IB00001","Happy Store","Susu Formula Enfamil A+","Merk susu pertumbuhan bayi diperkaya Prebiotik GOS tuk kesehatan pencernaan dan nutrisi penting lainnya","Ibu dan Anak",227000,5,70,30,30,R.drawable.enfamil,R.drawable.enfamil2,R.drawable.enfamil3));
//        listBarang.add(new Barang("GA00001","Games X Shop","Razer Blackwidow Chroma V2","Keyboard Gaming termahal yang berkualitas bintang 5","Games",1950000,100,230,10,100,R.drawable.blackwidowchroma,R.drawable.blackwidow2,R.drawable.blackwidow3));
//        listBarang.add(new Barang("GA00002","Games X Shop","Logitech Wireless M280 Mouse","Mouse standard yang dimiliki semua orang","Games",65000,350,1000,70,50,R.drawable.logitech,R.drawable.logitech2,R.drawable.logitech3));
//        listBarang.add(new Barang("GA00003","Games X Shop","Razer Deathadder Mouse","Mouse Razer versi murah","Games",128000,50,120,30,0,R.drawable.deathadder,R.drawable.deathadder2,R.drawable.deathadder3));
//        listRequestLelang.add(new ClassRequestLelang("FA00003"));
//        search=findViewById(R.id.etSearch);
//        filter=findViewById(R.id.btnFilter);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Barang");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Boolean cek = true;
                for (DataSnapshot ds:dataSnapshot.getChildren()){
//                    Barang isiBarang = new Barang();
//                    isiBarang.setStok("");
//                    isiBarang.setEmail(FirebaseAuth.getInstance().getCurrentUser().getEmail());
//                    isiBarang.setGender("");
//                    isiBarang.setNama(nama.getText().toString());
//                    isiBarang.setPassword(ds.child("password").getValue().toString());
//                    isiBarang.setUmur("");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        if(FirebaseAuth.getInstance().getCurrentUser()!=null){

            Toast.makeText(getApplicationContext(), "Welcome, "+FirebaseAuth.getInstance().getCurrentUser().getEmail(), Toast.LENGTH_SHORT).show();

        }

        Intent i = getIntent();
        if(i.hasExtra("adayanglogin")){
            aktif="1";
        }
        if(i.hasExtra("listUser")){
            listUser= (ArrayList<User>) i.getSerializableExtra("listUser");
            listToko= (ArrayList<Toko>) i.getSerializableExtra("listToko");
            listBarang= (ArrayList<Barang>) i.getSerializableExtra("listBarang");
            listWishlist= (ArrayList<ClassWishlist>) i.getSerializableExtra("listWishlist");
            listRequestLelang= (ArrayList<ClassRequestLelang>) i.getSerializableExtra("listRequestLelang");
        }




//        search.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                adasearch=true;
//                adafilter=false;
//                if(listBarangFilter.size()>0){
//                    listBarangSearch.clear();
//                    for (int j = 0; j < listBarangFilter.size(); j++) {
//                        if(listBarangFilter.get(j).namabarang.toLowerCase().contains(search.getText().toString().toLowerCase())){
//                            listBarangSearch.add(listBarangFilter.get(j));
//                        }
//                    }
//                    AdapterMenuBarang adapterMenuBarang= new AdapterMenuBarang(listBarangSearch);
//                    rv.setAdapter(adapterMenuBarang);
//                }
//                else{
//                    listBarangSearch.clear();
//                    for (int j = 0; j < listBarang.size(); j++) {
//                        if(listBarang.get(j).namabarang.toLowerCase().contains(search.getText().toString().toLowerCase())){
//                            listBarangSearch.add(listBarang.get(j));
//                        }
//                    }
//                    AdapterMenuBarang adapterMenuBarang= new AdapterMenuBarang(listBarangSearch);
//                    rv.setAdapter(adapterMenuBarang);
//                }
//            }
//        });

//        adapterMenuBarang = new AdapterMenuBarang(listBarang, new RVClickListener() {
//            @Override
//            public void recyclerViewListBarangClick(View v, int posisi) {
//                int indeks=0;
//
//                if(adasearch==true){
//                    if(!listBarangSearch.isEmpty()){
//                        if(listBarangFilter.isEmpty()){
//                            for (int j = 0; j < listBarang.size(); j++) {
//                                if (listBarangSearch.get(posisi).idbarang.equals(listBarang.get(j).idbarang)) {
//                                    indeks = j;
//                                }
//                            }
//                        }
//                        else{
//                            int temp=0;
//                            for (int j = 0; j < listBarangFilter.size(); j++) {
//                                if (listBarangSearch.get(posisi).idbarang.equals(listBarangFilter.get(j).idbarang)) {
//                                    temp=j;
//                                }
//                            }
//                            for (int j = 0; j < listBarang.size(); j++) {
//                                if(listBarangFilter.get(temp).idbarang.equals(listBarang.get(j).idbarang)){
//                                    indeks=j;
//                                }
//                            }
//                        }
//                    }
//                    else{
//                        indeks = posisi;
//                    }
//                }
//                else if(adafilter==true){
//                    if(!listBarangFilter.isEmpty()){
//                        if(listBarangSearch.isEmpty()){
//                            for (int j = 0; j < listBarang.size(); j++) {
//                                if (listBarangFilter.get(posisi).idbarang.equals(listBarang.get(j).idbarang)) {
//                                    indeks = j;
//                                }
//                            }
//                        }
//                        else{
//                            int temp=0;
//                            for (int j = 0; j < listBarangSearch.size(); j++) {
//                                if (listBarangFilter.get(posisi).idbarang.equals(listBarangSearch.get(j).idbarang)) {
//                                    temp = j;
//                                }
//                            }
//                            for (int j = 0; j < listBarang.size(); j++) {
//                                if(listBarangSearch.get(temp).idbarang.equals(listBarang.get(j).idbarang)){
//                                    indeks=j;
//                                }
//                            }
//                        }
//                    }
//                    else{
//                        indeks = posisi;
//                    }
//                }
//                else{
//                    indeks = posisi;
//                }
//
//                Intent i = new Intent(Home.this,InfoBarang.class);
//                i.putExtra("indeks",indeks);
//                i.putExtra("listUser", listUser);
//                i.putExtra("listWishlist", listWishlist);
//                i.putExtra("listToko", listToko);
//                i.putExtra("listBarang", listBarang);
//                i.putExtra("listRequestLelang", listRequestLelang);
//                i.putExtra("adayanglogin","1");
//                startActivity(i);
//            }
//        });

        changeFragment(new FragmentHome(),listBarang,listUser);
        bottomNavHome = findViewById(R.id.bottomNavHome);
        bottomNavHome.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(menuItem.getItemId( )== R.id.itemHome){
                    changeFragment(new FragmentHome(),listBarang,listUser);
                }else if(menuItem.getItemId( )== R.id.itemLelang){
                    changeFragment(new FragmentLelang(),listBarang,listUser);
                }else if(menuItem.getItemId( )== R.id.itemEvent){
                    changeFragment(new FragmentEvent(),listBarang,listUser);
                }else if(menuItem.getItemId( )== R.id.itemHistory){
                    changeFragment(new FragmentHistory(),listBarang,listUser);
                }
//                else if(adafilter==true){
//                    if(!listBarangFilter.isEmpty()){
//                        if(listBarangSearch.isEmpty()){
//                            for (int j = 0; j < listBarang.size(); j++) {
//                                if (listBarangFilter.get(posisi).idbarang.equals(listBarang.get(j).idbarang)) {
//                                    indeks = j;
//                                }
//                            }
//                        }
//                        else{
//                            int temp=0;
//                            for (int j = 0; j < listBarangSearch.size(); j++) {
//                                if (listBarangFilter.get(posisi).idbarang.equals(listBarangSearch.get(j).idbarang)) {
//                                    temp = j;
//                                }
//                            }
//                            for (int j = 0; j < listBarang.size(); j++) {
//                                if(listBarangSearch.get(temp).idbarang.equals(listBarang.get(j).idbarang)){
//                                    indeks=j;
//                                }
//                            }
//                        }
//                    }
//                    else{
//                        indeks = posisi;
//                    }
//                }
//                else{
//                    indeks = posisi;
//                }
//
//                Intent i = new Intent(Home.this,InfoBarang.class);
//                i.putExtra("indeks",indeks);
//                i.putExtra("listUser", listUser);
//                i.putExtra("listWishlist", listWishlist);
//                i.putExtra("listToko", listToko);
//                i.putExtra("listBarang", listBarang);
//                i.putExtra("listRequestLelang", listRequestLelang);
//                i.putExtra("adayanglogin",aktif);
//                startActivity(i);
                Date a = new Date();
                SimpleDateFormat time = new SimpleDateFormat("hh:mm aa");
                Calendar now = Calendar.getInstance();
                Toast.makeText(Home.this,now.get(Calendar.DAY_OF_MONTH)+"/"+now.get(Calendar.MONTH)+"/"+now.get(Calendar.YEAR)+" - "+time.format(a), Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu,menu);

        if(FirebaseAuth.getInstance().getCurrentUser()!=null){
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("User");
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Boolean cek = true;
                    for (DataSnapshot ds:dataSnapshot.getChildren()){
                        if(ds.child("email").getValue().toString().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())){
                            String  nama = ds.child("nama").getValue().toString();
                            menu.getItem(3).setTitle("Hai, "+nama+" !");
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

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
        else{
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
                adasearch=false;
                adafilter=true;
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
                        adafilter=true;
                        adasearch=false;
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
            FirebaseAuth.getInstance().signOut();
            Intent i = new Intent(Home.this,Home.class);
            startActivity(i);
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

    public void changeFragment(Fragment f, ArrayList<Barang>listBarang, ArrayList<User>listUser){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putSerializable("listBarang",listBarang);
        bundle.putSerializable("listUser",listUser);
        f.setArguments(bundle);
        ft.replace(R.id.containerHome, f);
        ft.commit();
    }
}