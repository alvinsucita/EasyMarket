package com.example.easymarket;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    EditText email,pass;
    Button login;
    String stremail,strpass;
    DatabaseReference databaseReference_user,databaseReference_toko;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=findViewById(R.id.etEmail);
        pass=findViewById(R.id.etPassword);
        login=findViewById(R.id.btnLogin);

        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setCornerRadius(100);
        drawable.setColor(Color.WHITE);
        email.setBackground(drawable);

        GradientDrawable drawable2 = new GradientDrawable();
        drawable2.setShape(GradientDrawable.RECTANGLE);
        drawable2.setCornerRadius(100);
        drawable2.setColor(Color.WHITE);
        pass.setBackground(drawable2);

        GradientDrawable drawable3 = new GradientDrawable();
        drawable3.setShape(GradientDrawable.RECTANGLE);
        drawable3.setCornerRadius(100);
        drawable3.setColor(Color.BLACK);
        login.setBackground(drawable3);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void toRegis(View view) {
        Intent i = new Intent(Login.this, Register.class);
        startActivity(i);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id== android.R.id.home){
            Intent i = new Intent(Login.this, Home.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    public void login(View view) {
        databaseReference_user = FirebaseDatabase.getInstance().getReference().child("ClassUser");
        databaseReference_toko = FirebaseDatabase.getInstance().getReference().child("ClassToko");
        stremail=email.getText().toString();
        strpass=pass.getText().toString();

        if(stremail.equals("") || strpass.equals("")){
            Toast.makeText(this, "Isi semua field terlebih dahulu", Toast.LENGTH_SHORT).show();
        }
        else if(stremail.toLowerCase().equals("admin") || strpass.toLowerCase().equals("admin")){
            Intent i = new Intent(Login.this, HomeAdmin.class);
            startActivity(i);
        }
        else{
            //login user
            databaseReference_user.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Boolean cek = false;
                    for (DataSnapshot ds:dataSnapshot.getChildren()){
                        if(stremail.equals(ds.child("email").getValue().toString())&&strpass.equals(ds.child("password").getValue().toString())&&ds.child("aktif").getValue().toString().equals("1")){
                            cek=true;
                            FirebaseAuth.getInstance().signInWithEmailAndPassword(stremail,strpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Intent i = new Intent(Login.this,Home.class);
                                    startActivity(i);
                                }
                            });
                        }
                    }
                    if(cek==false){
                        //login toko
                        databaseReference_toko.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot2) {
                                for (DataSnapshot ds2:dataSnapshot2.getChildren()){
                                    if(stremail.equals(ds2.child("email").getValue().toString())&&strpass.equals(ds2.child("password").getValue().toString())&&ds2.child("aktif").getValue().toString().equals("1")){
                                        FirebaseAuth.getInstance().signInWithEmailAndPassword(stremail,strpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                Intent i = new Intent(Login.this,HomeToko.class);
                                                startActivity(i);
                                            }
                                        });
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }
}