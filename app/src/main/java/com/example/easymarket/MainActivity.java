package com.example.easymarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView iv,iv2,iv3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = findViewById(R.id.imageViewTes);
        iv2 = findViewById(R.id.ivpemilik);
        iv3 = findViewById(R.id.imageView3);
        registerForContextMenu(iv);
        registerForContextMenu(iv2);
        registerForContextMenu(iv3);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu_transaction,menu);
    }

    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.MenuPreview){
            Intent intent = new Intent(MainActivity.this,PreviewActivity.class);
            startActivity(intent);
        }else if(item.getItemId() == R.id.MenuTransaction){
            Intent intent = new Intent(MainActivity.this,TransactionActivity.class);
            startActivity(intent);
        }
        return true;
    }

}
