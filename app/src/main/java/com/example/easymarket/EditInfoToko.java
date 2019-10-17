package com.example.easymarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditInfoToko extends AppCompatActivity {

    EditText deskripsi, alamat;
    Button edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info_toko);

        deskripsi = findViewById(R.id.eteditdesc);
        alamat = findViewById(R.id.eteditaddress);
        edit = findViewById(R.id.btneditinfo);

        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setStroke(5, Color.BLACK);
        drawable.setCornerRadius(15);
        deskripsi.setBackground(drawable);
        alamat.setBackground(drawable);

        GradientDrawable drawable2 = new GradientDrawable();
        drawable2.setShape(GradientDrawable.OVAL);
        drawable2.setStroke(5, Color.BLACK);
        drawable2.setCornerRadius(15);
        edit.setBackground(drawable2);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.temp_usertoko,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.viewpage){
            Intent i = new Intent(EditInfoToko.this,pagetoko.class);
            startActivity(i);
        }else if(item.getItemId()==R.id.viewproduct){
            Intent i = new Intent(EditInfoToko.this,TempProduct.class);
            startActivity(i);
        }else if(item.getItemId() == R.id.viewreview){
            Intent i = new Intent(EditInfoToko.this,review_toko.class);
            startActivity(i);
        }else if(item.getItemId() == R.id.viewinfo) {
            Intent i = new Intent(EditInfoToko.this, EditInfoToko.class);
            startActivity(i);
        }else if(item.getItemId() == R.id.returnstore) {
            Intent i = new Intent(EditInfoToko.this, HomeToko.class);
            startActivity(i);
        }else if(item.getItemId() == R.id.logout) {
            Intent i = new Intent(EditInfoToko.this, Login.class);
            startActivity(i);
        }
        return true;
    }
}
