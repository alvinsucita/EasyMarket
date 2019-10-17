package com.example.easymarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class pagetoko extends AppCompatActivity {

    TextView namatoko;
    RecyclerView rv;
    ImageButton btchat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagetoko);

        namatoko = findViewById(R.id.tvnamatoko);
        rv = findViewById(R.id.rvtoko);
        btchat = findViewById(R.id.btnchat);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setStroke(5, Color.BLACK);
        drawable.setCornerRadius(15);
        namatoko.setBackground(drawable);

        showCardViewProduct();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void showCardViewProduct(){
        rv.setLayoutManager(new LinearLayoutManager(this));
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.temp_tabtoko,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.product){
//            showCardViewProduct();
            Intent i = new Intent(pagetoko.this,TempProduct.class);
            startActivity(i);
        }else if(item.getItemId() == R.id.review){
            Intent i = new Intent(pagetoko.this,review_toko.class);
            startActivity(i);
        }else{
            Intent i = new Intent(pagetoko.this,info_toko.class);
            startActivity(i);
        }
        return true;
    }

    public void movetoChat(View view){
        Intent i = new Intent(pagetoko.this,ChatRoom.class);
        startActivity(i);
    }
}
