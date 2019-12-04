package com.example.easymarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class InfoBarang extends AppCompatActivity {
    BottomNavigationView bottomNavBarang;
    TextView nama,hargabarang;
    Button share,chat,add;
    ArrayList<User> listUser = new ArrayList<>();
    ArrayList<Barang> listBarang = new ArrayList<>();
    ArrayList<ClassWishlist> listWishlist = new ArrayList<>();
    ArrayList<Toko> listToko = new ArrayList<>();
    ArrayList<ClassLikes>listLikes = new ArrayList<>();
    int jumlah= 0;
    String aktif="0";
    int indeks=0;
    ImageView foto1,foto2,foto3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_barang);

        nama=findViewById(R.id.tvNamaBarang);
        hargabarang=findViewById(R.id.tvHargaBarang);
        add=findViewById(R.id.btnAddToWishlist);
        chat=findViewById(R.id.btnPersonalChat);
        share=findViewById(R.id.btnShare);
        foto1=findViewById(R.id.ivFoto1);
        foto2=findViewById(R.id.ivFoto2);
        foto3=findViewById(R.id.ivFoto3);
        nama.setText("");
        hargabarang.setText("");

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        GradientDrawable drawable2 = new GradientDrawable();
        drawable2.setShape(GradientDrawable.RECTANGLE);
        drawable2.setCornerRadius(100);
        drawable2.setStroke(8, Color.LTGRAY);
        drawable2.setColor(Color.WHITE);
        add.setBackground(drawable2);

        GradientDrawable drawable3 = new GradientDrawable();
        drawable3.setShape(GradientDrawable.RECTANGLE);
        drawable3.setCornerRadius(100);
        drawable3.setStroke(8, Color.LTGRAY);
        drawable3.setColor(Color.WHITE);
        share.setBackground(drawable3);

        Intent i = getIntent();
        listUser= (ArrayList<User>) i.getSerializableExtra("listUser");
        listToko= (ArrayList<Toko>) i.getSerializableExtra("listToko");
        listBarang= (ArrayList<Barang>) i.getSerializableExtra("listBarang");
        listWishlist= (ArrayList<ClassWishlist>) i.getSerializableExtra("listWishlist");
        indeks=i.getIntExtra("indeks",indeks);
        if(i.hasExtra("adayanglogin")){
            aktif=i.getStringExtra("adayanglogin");
        }
        listBarang.get(indeks).dilihat+=1;
        String hargaasli = String.format("%,d", listBarang.get(indeks).harga);
        nama.setText(listBarang.get(indeks).namabarang);
        hargabarang.setText("Rp. "+hargaasli);

        changeFragment(new FragmentInfoBarang(),listBarang,listUser);
        bottomNavBarang=findViewById(R.id.bottomNavBarang);
        bottomNavBarang.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(menuItem.getItemId( )== R.id.infobarang){
                    changeFragment(new FragmentInfoBarang(),listBarang,listUser);
                }
                else if(menuItem.getItemId( )== R.id.commentbarang){
                    changeFragment(new FragmentCommentBarang(),listBarang,listUser);
                }
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home){
            Intent i = new Intent(InfoBarang.this,Home.class);
            i.putExtra("listUser", listUser);
            i.putExtra("listToko", listToko);
            i.putExtra("listBarang", listBarang);
            i.putExtra("listWishlist", listWishlist);
            if(aktif.equals("1")){
                i.putExtra("adayanglogin","1");
            }
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    public void toChat(View view) {
        if(aktif.equals("1")){
            Intent i = new Intent(InfoBarang.this,ChatRoom.class);
            startActivity(i);
        }
        else {
            Toast.makeText(this, "Login terlebih dahulu", Toast.LENGTH_SHORT).show();
        }
    }
    public void preview(View view) {
        Intent i = new Intent(InfoBarang.this,PreviewActivity.class);
        startActivity(i);
    }
    public void beli(View view) {
        Intent i = new Intent(InfoBarang.this,TransactionActivity.class);
        startActivity(i);
    }

    public void addwishlist(View view) {
        if(aktif.equals("1")){
            String useryangbeli="",barangyangdibeli="";
            for (int i = 0; i < listUser.size(); i++) {
                if(listUser.get(i).aktif.equals("1")){
                    useryangbeli = listUser.get(i).nama;
                }
            }
            barangyangdibeli=nama.getText().toString();
            if(listWishlist.size()==0){
                listWishlist.add(new ClassWishlist(barangyangdibeli,useryangbeli));
                Toast.makeText(this, "Barang berhasil ditambahkan ke Wishlist", Toast.LENGTH_SHORT).show();
            }
            else{
                jumlah=0;
                for (int i = 0; i < listWishlist.size(); i++) {
                    if(listWishlist.get(i).yangbeli.equals(useryangbeli)){
                        jumlah++;
                    }
                }
                if(jumlah>0){
                    for (int i = 0; i < listWishlist.size(); i++) {
                        if(listWishlist.get(i).yangbeli.equals(useryangbeli) && listWishlist.get(i).namabarang.equals(nama.getText().toString())){
                            Toast.makeText(this, "Barang sudah ada di Wishlist", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        else if(i+1==listWishlist.size()){
                            Toast.makeText(this, "Barang berhasil ditambahkan ke Wishlist", Toast.LENGTH_SHORT).show();
                            listWishlist.add(new ClassWishlist(barangyangdibeli,useryangbeli));
                            break;
                        }
                    }
                }
                else{
                    listWishlist.add(new ClassWishlist(barangyangdibeli,useryangbeli));
                    Toast.makeText(this, "Barang berhasil ditambahkan ke Wishlist", Toast.LENGTH_SHORT).show();
                }
            }
        }
        else {
            Toast.makeText(this, "Login terlebih dahulu", Toast.LENGTH_SHORT).show();
        }
    }

    public void share(View view) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,listBarang.get(indeks).namabarang+"\n"+listBarang.get(indeks).harga );
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, "Share"));
    }

    public void changeFragment(Fragment f, ArrayList<Barang>listBarang,ArrayList<User>listUser){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putSerializable("listBarang",listBarang);
        bundle.putSerializable("listUser",listUser);
        f.setArguments(bundle);
        ft.replace(R.id.containerBarang, f);
        ft.commit();
    }
}