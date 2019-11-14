package com.example.easymarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    EditText search;
    Button btnsearch,nextpage,backpage;
    String userlogin;
    ArrayList<User> listUser = new ArrayList<>();
    ArrayList<Barang> listBarang = new ArrayList<>();
    ArrayList<Toko> listToko = new ArrayList<>();
    ArrayList<ClassWishlist> listWishlist = new ArrayList<>();
    String aktif="0";
    ImageView barang1,barang2,barang3,barang4,barang5,barang6;
    int page=0;
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
        listBarang.add(new Barang("FA00001","Hypershop","Sendal Swallow","Sendal jepit yang sangat murah dan kualitas pas-pas an","Fashion",11000,0,0,0,10));
        listBarang.add(new Barang("FA00002","Hypershop","Adidas Terrex Free Hiker GTX","Sepatu yang cocok untuk pria yang aktif berpetualang","Fashion",2800000,7,30,3,2));
        listBarang.add(new Barang("FA00003","Hypershop","Balenciaga Triple S","Sepatu mahal yang sangat waw","Fashion",11000000,200,500,1,10));
        listBarang.add(new Barang("IB00001","Happy Store","Susu Formula Enfamil A+","Merk susu pertumbuhan bayi diperkaya Prebiotik GOS tuk kesehatan pencernaan dan nutrisi penting lainnya","Ibu dan Bayi",227000,5,70,30,30));
        listBarang.add(new Barang("GA00001","Games X Shop","Razer Blackwidow Chroma V2","Keyboard Gaming termahal yang berkualitas bintang 5","Gaming",1950000,100,230,10,100));
        listBarang.add(new Barang("GA00002","Games X Shop","Logitech Wireless M280 Mouse","Mouse standard yang dimiliki semua orang","Gaming",65000,350,1000,70,50));
        listBarang.add(new Barang("GA00003","Games X Shop","Razer Deathadder Mouse","Mouse Razer versi murah","Gaming",128000,50,120,30,0));
        search=findViewById(R.id.etSearch);
        btnsearch=findViewById(R.id.btnSearch);
        nextpage=findViewById(R.id.btnNextPage);
        backpage=findViewById(R.id.btnPrevious);
        barang1=findViewById(R.id.ivBarang1);
        barang2=findViewById(R.id.ivBarang2);
        barang3=findViewById(R.id.ivBarang3);
        barang4=findViewById(R.id.ivBarang4);
        barang5=findViewById(R.id.ivBarang5);
        barang6=findViewById(R.id.ivBarang6);

        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(Color.WHITE);
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setCornerRadius(15);
        search.setBackground(drawable);

        GradientDrawable drawable2 = new GradientDrawable();
        drawable2.setColor(Color.WHITE);
        drawable2.setShape(GradientDrawable.OVAL);
        drawable2.setStroke(5, Color.BLACK);
        nextpage.setBackground(drawable2);

        GradientDrawable drawable3 = new GradientDrawable();
        drawable3.setColor(Color.WHITE);
        drawable3.setShape(GradientDrawable.OVAL);
        drawable3.setStroke(5, Color.BLACK);
        backpage.setBackground(drawable3);

        Intent i = getIntent();
        if(i.hasExtra("listUser")){
            listUser= (ArrayList<User>) i.getSerializableExtra("listUser");
            listToko= (ArrayList<Toko>) i.getSerializableExtra("listToko");
            listBarang= (ArrayList<Barang>) i.getSerializableExtra("listBarang");
            listWishlist= (ArrayList<ClassWishlist>) i.getSerializableExtra("listWishlist");
            for (int j = 0; j < listUser.size(); j++) {
                if(listUser.get(j).aktif.equals("1")){
                    userlogin=listUser.get(j).nama;
                }
            }
        }
        if(i.hasExtra("adayanglogin")){
            aktif="1";
        }
        if(i.hasExtra("nextpage")){
            page=i.getIntExtra("nextpage",page)+1;
        }
        if(i.hasExtra("previouspage")){
            page=i.getIntExtra("previouspage",page)-1;
        }

        if(listBarang.size()>=page*6+1){
            barang1.setBackgroundColor(Color.WHITE);
        }
        if(listBarang.size()>=page*6+2){
            barang2.setBackgroundColor(Color.WHITE);
        }
        if(listBarang.size()>=page*6+3){
            barang3.setBackgroundColor(Color.WHITE);
        }
        if(listBarang.size()>=page*6+4){
            barang4.setBackgroundColor(Color.WHITE);
        }
        if(listBarang.size()>=page*6+5){
            barang5.setBackgroundColor(Color.WHITE);
        }
        if(listBarang.size()>=page*6+6){
            barang6.setBackgroundColor(Color.WHITE);
        }
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

    public Intent putextra(Intent i){
        i.putExtra("listUser", listUser);
        i.putExtra("listWishlist", listWishlist);
        i.putExtra("listToko", listToko);
        i.putExtra("listBarang", listBarang);
        Toast.makeText(getApplicationContext(), "ok", Toast.LENGTH_SHORT).show();
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
        else if(item.getItemId()==R.id.itemWishlist){
            Intent i = new Intent(Home.this,WishList.class);
            i = putextra(i);
            i.putExtra("adayanglogin",aktif);
            startActivity(i);
        }
        else if(item.getItemId()==R.id.itemLelang){
            Intent i = new Intent(Home.this,lelang.class);
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

    public void infoBarang1(View view) {
        if(listBarang.size()>=page*6+1){
            Intent i = new Intent(Home.this,InfoBarang.class);
            i.putExtra("listUser", listUser);
            i.putExtra("listToko", listToko);
            i.putExtra("listWishlist", listWishlist);
            i.putExtra("listBarang", listBarang);
            i.putExtra("adayanglogin",aktif);
            i.putExtra("barangyangdipilih",page*6+1-1);
            startActivity(i);
        }
    }
    public void infoBarang2(View view) {
        if(listBarang.size()>=page*6+2){
            Intent i = new Intent(Home.this,InfoBarang.class);
            i.putExtra("listUser", listUser);
            i.putExtra("listToko", listToko);
            i.putExtra("listWishlist", listWishlist);
            i.putExtra("listBarang", listBarang);
            i.putExtra("adayanglogin",aktif);
            i.putExtra("barangyangdipilih",page*6+2-1);
            startActivity(i);
        }
    }
    public void infoBarang3(View view) {
        if(listBarang.size()>=page*6+3){
            Intent i = new Intent(Home.this,InfoBarang.class);
            i.putExtra("listUser", listUser);
            i.putExtra("listToko", listToko);
            i.putExtra("listBarang", listBarang);
            i.putExtra("listWishlist", listWishlist);
            i.putExtra("adayanglogin",aktif);
            i.putExtra("barangyangdipilih",page*6+3-1);
            startActivity(i);
        }
    }
    public void infoBarang4(View view) {
        if(listBarang.size()>=page*6+4){
            Intent i = new Intent(Home.this,InfoBarang.class);
            i.putExtra("listUser", listUser);
            i.putExtra("listWishlist", listWishlist);
            i.putExtra("listToko", listToko);
            i.putExtra("listBarang", listBarang);
            i.putExtra("adayanglogin",aktif);
            i.putExtra("barangyangdipilih",page*6+4-1);
            startActivity(i);
        }
    }
    public void infoBarang5(View view) {
        if(listBarang.size()>=page*6+5){
            Intent i = new Intent(Home.this,InfoBarang.class);
            i.putExtra("listUser", listUser);
            i.putExtra("listWishlist", listWishlist);
            i.putExtra("listToko", listToko);
            i.putExtra("listBarang", listBarang);
            i.putExtra("adayanglogin",aktif);
            i.putExtra("barangyangdipilih",page*6+5-1);
            startActivity(i);
        }
    }
    public void infoBarang6(View view) {
        if(listBarang.size()>=page*6+6){
            Intent i = new Intent(Home.this,InfoBarang.class);
            i.putExtra("listUser", listUser);
            i.putExtra("listToko", listToko);
            i.putExtra("listWishlist", listWishlist);
            i.putExtra("listBarang", listBarang);
            i.putExtra("adayanglogin",aktif);
            i.putExtra("barangyangdipilih",page*6+6-1);
            startActivity(i);
        }
    }
    public void nextpage(View view) {
        if(listBarang.size()>=(page+1)*6+1){
            Intent i = new Intent(Home.this,Home.class);
            i.putExtra("nextpage",page);
            i.putExtra("listUser", listUser);
            i.putExtra("listToko", listToko);
            i.putExtra("listWishlist", listWishlist);
            i.putExtra("listBarang", listBarang);
            if(aktif.equals("1")){
                i.putExtra("adayanglogin","1");
            }
            startActivity(i);
        }
    }

    public void previouspage(View view) {
        if(page!=0){
            Intent i = new Intent(Home.this,Home.class);
            i.putExtra("previouspage",page);
            i.putExtra("listUser", listUser);
            i.putExtra("listWishlist", listWishlist);
            i.putExtra("listToko", listToko);
            i.putExtra("listBarang", listBarang);
            if(aktif.equals("1")){
                i.putExtra("adayanglogin","1");
            }
            startActivity(i);
        }
    }

    public void cari(View view) {

    }
}