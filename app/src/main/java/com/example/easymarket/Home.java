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

public class Home extends AppCompatActivity {

    EditText search;
    Button btnsearch,nextpage;
    String userlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        search=findViewById(R.id.etSearch);
        btnsearch=findViewById(R.id.btnSearch);
        nextpage=findViewById(R.id.btnNextPage);

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu,menu);

        menu.getItem(0).setVisible(true);
        menu.getItem(1).setVisible(false);
        menu.getItem(2).setVisible(false);
        menu.getItem(3).setVisible(false);
        menu.getItem(4).setVisible(false);
        Intent i = getIntent();
        if(i.hasExtra("passuser")){
            userlogin=i.getStringExtra("passuser");
            menu.getItem(3).setTitle("Hello, "+userlogin+" !");
            menu.getItem(3).setVisible(true);

            menu.getItem(0).setVisible(false);
            menu.getItem(1).setVisible(true);
            menu.getItem(2).setVisible(true);
            menu.getItem(4).setVisible(true);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.itemLogin){
            Intent i = new Intent(Home.this,Login.class);
            startActivity(i);
        }
        else if(item.getItemId()==R.id.itemLogout){
            Intent i = new Intent(Home.this,Home.class);
            startActivity(i);
        }
        else if(item.getItemId()==R.id.itemEvent){
            Intent i = new Intent(Home.this,EventPage.class);
            startActivity(i);
        }
        else if(item.getItemId()==R.id.itemWishlist){
            Intent i = new Intent(Home.this,WishList.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    public void infoBarang1(View view) {
        Intent i = new Intent(Home.this,InfoBarang.class);
        startActivity(i);
    }
}
