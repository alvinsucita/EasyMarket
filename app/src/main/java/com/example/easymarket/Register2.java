package com.example.easymarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register2 extends AppCompatActivity {

    Button register;
    EditText username,email,password,conpassword;
    String strusername,stremail,strpassword,strconpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        register=findViewById(R.id.btnRegister);

        username=findViewById(R.id.etUsername);
        email=findViewById(R.id.etEmail);
        password=findViewById(R.id.etPassword);
        conpassword=findViewById(R.id.etKonfirmasiPassword);

        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(Color.WHITE);
        drawable.setShape(GradientDrawable.OVAL);
        drawable.setStroke(5, Color.BLACK);
        register.setBackground(drawable);

        GradientDrawable drawable2 = new GradientDrawable();
        drawable2.setColor(Color.WHITE);
        drawable2.setShape(GradientDrawable.RECTANGLE);
        drawable2.setStroke(5, Color.BLACK);
        drawable2.setCornerRadius(15);
        username.setBackground(drawable2);

        GradientDrawable drawable3 = new GradientDrawable();
        drawable3.setColor(Color.WHITE);
        drawable3.setShape(GradientDrawable.RECTANGLE);
        drawable3.setStroke(5, Color.BLACK);
        drawable3.setCornerRadius(15);
        email.setBackground(drawable3);

        GradientDrawable drawable4 = new GradientDrawable();
        drawable4.setColor(Color.WHITE);
        drawable4.setShape(GradientDrawable.RECTANGLE);
        drawable4.setStroke(5, Color.BLACK);
        drawable4.setCornerRadius(15);
        password.setBackground(drawable4);

        GradientDrawable drawable5 = new GradientDrawable();
        drawable5.setColor(Color.WHITE);
        drawable5.setShape(GradientDrawable.RECTANGLE);
        drawable5.setStroke(5, Color.BLACK);
        drawable5.setCornerRadius(15);
        conpassword.setBackground(drawable5);


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void register(View view) {
        strusername=username.getText().toString();
        stremail=email.getText().toString();
        strpassword=password.getText().toString();
        strconpassword=conpassword.getText().toString();
        if(strusername.equals("") || stremail.equals("")|| strpassword.equals("") || strconpassword.equals("")){
            Toast.makeText(this, "Isi Semua Field Terlebih Dahulu ! ", Toast.LENGTH_SHORT).show();
        }
        else{
            if(!strpassword.equals(strconpassword)){
                Toast.makeText(this, "Password dan Konfirmasi Password Harus Sama ! ", Toast.LENGTH_SHORT).show();
            }
            else{
                Intent i = new Intent(Register2.this,Login.class);
                i.putExtra("passuser",strusername);
                i.putExtra("passpass",strpassword);
                startActivity(i);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id== android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
