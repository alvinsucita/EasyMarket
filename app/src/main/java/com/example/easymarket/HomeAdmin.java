package com.example.easymarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class HomeAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menuadmin,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.addevent){
            Intent i = new Intent(HomeAdmin.this,InputEvent.class);
            startActivity(i);
        }
        else if(item.getItemId()==R.id.changelog){
            Intent i = new Intent(HomeAdmin.this,MasterChangelog.class);
            startActivity(i);
        }
        else if(item.getItemId()==R.id.manageuser){
            Intent i = new Intent(HomeAdmin.this,ManageUsers.class);
            startActivity(i);
        }
        else if(item.getItemId()==R.id.managetickets){
            Intent i = new Intent(HomeAdmin.this,ManageTickets.class);
            startActivity(i);
        }
        else if(item.getItemId()==R.id.managelelang){
            Intent i = new Intent(HomeAdmin.this,MasterLelang.class);
            startActivity(i);
        }
        else if(item.getItemId()==R.id.managetrans){
            Intent i = new Intent(HomeAdmin.this,MasterTrans.class);
            startActivity(i);
        }
        else if(item.getItemId()==R.id.managekas){
            Intent i = new Intent(HomeAdmin.this,MasterKas.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

}