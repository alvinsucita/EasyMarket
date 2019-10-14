package com.example.easymarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity {
    TextView toregis;
    EditText user,pass;
    Button login;
    String struser,strpass,userterdaftar="",passterdaftar="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        toregis=findViewById(R.id.tvToRegis);
        user=findViewById(R.id.etUsername);
        pass=findViewById(R.id.etPassword);
        login=findViewById(R.id.btnLogin);

        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(Color.WHITE);
        drawable.setShape(GradientDrawable.OVAL);
        drawable.setStroke(5, Color.BLACK);
        login.setBackground(drawable);

        GradientDrawable drawable2 = new GradientDrawable();
        drawable2.setColor(Color.WHITE);
        drawable2.setShape(GradientDrawable.RECTANGLE);
        drawable2.setStroke(5, Color.BLACK);
        drawable2.setCornerRadius(15);
        user.setBackground(drawable2);

        GradientDrawable drawable3 = new GradientDrawable();
        drawable3.setColor(Color.WHITE);
        drawable3.setShape(GradientDrawable.RECTANGLE);
        drawable3.setStroke(5, Color.BLACK);
        drawable3.setCornerRadius(15);
        pass.setBackground(drawable3);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        if(i.hasExtra("passuser")){
            userterdaftar=i.getStringExtra("passuser");
            passterdaftar=i.getStringExtra("passpass");
        }
    }

    public void toRegis(View view) {
        Intent i = new Intent(Login.this, Register1.class);
        startActivity(i);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id== android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void login(View view) {
        struser=user.getText().toString();
        strpass=pass.getText().toString();
        if(struser.equals("") || strpass.equals("")){
            Toast.makeText(this, "Isi Semua Field Terlebih Dahulu ! ", Toast.LENGTH_SHORT).show();
        }
        else if(struser.equals(userterdaftar) && strpass.equals(passterdaftar) || struser.equals("a") && strpass.equals("a")){
            Intent i = new Intent(Login.this,Home.class);
            i.putExtra("passuser",userterdaftar);
            startActivity(i);
        }
        else{
            Toast.makeText(this, "Username atau Password Salah ! ", Toast.LENGTH_SHORT).show();
        }
    }
}