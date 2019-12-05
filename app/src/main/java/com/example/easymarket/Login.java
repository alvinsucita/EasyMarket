package com.example.easymarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.collection.LLRBNode;

import java.util.ArrayList;

public class Login extends AppCompatActivity {
    EditText email,pass;
    Button login;
    String stremail,strpass;
    ArrayList<User> listUser = new ArrayList<>();
    ArrayList<Toko> listToko = new ArrayList<>();
    ArrayList<Barang> listBarang = new ArrayList<>();
    ArrayList<ClassWishlist> listWishlist = new ArrayList<>();
    ArrayList<ClassRequestLelang> listRequestLelang = new ArrayList<>();
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

        Intent i = getIntent();
        if(i.hasExtra("listUser")){
            listUser= (ArrayList<User>) i.getSerializableExtra("listUser");
            listToko= (ArrayList<Toko>) i.getSerializableExtra("listToko");
            listBarang= (ArrayList<Barang>) i.getSerializableExtra("listBarang");
            listWishlist= (ArrayList<ClassWishlist>) i.getSerializableExtra("listWishlist");
            listRequestLelang= (ArrayList<ClassRequestLelang>) i.getSerializableExtra("listRequestLelang");
        }
    }

    public void toRegis(View view) {
        Intent i = new Intent(Login.this, Register.class);
        i.putExtra("listUser", listUser);
        i.putExtra("listToko", listToko);
        i.putExtra("listBarang", listBarang);
        i.putExtra("listWishlist", listWishlist);
        i.putExtra("listRequestLelang", listRequestLelang);
        startActivity(i);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id== android.R.id.home){
            Intent i = new Intent(Login.this, Home.class);
            i.putExtra("listUser", listUser);
            i.putExtra("listToko", listToko);
            i.putExtra("listBarang", listBarang);
            i.putExtra("listWishlist", listWishlist);
            i.putExtra("listRequestLelang", listRequestLelang);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    public void login(View view) {
        databaseReference_user = FirebaseDatabase.getInstance().getReference().child("User");
        databaseReference_toko = FirebaseDatabase.getInstance().getReference().child("Toko");
        stremail=email.getText().toString();
        strpass=pass.getText().toString();

        if(stremail.equals("") || strpass.equals("")){
            Toast.makeText(this, "Isi Semua Field Terlebih Dahulu ! ", Toast.LENGTH_SHORT).show();
        }
        else if(stremail.toLowerCase().equals("admin") || strpass.toLowerCase().equals("admin")){
            Intent i = new Intent(Login.this, HomeAdmin.class);
            i.putExtra("listUser", listUser);
            i.putExtra("listToko", listToko);
            i.putExtra("listBarang", listBarang);
            i.putExtra("listWishlist", listWishlist);
            i.putExtra("listRequestLelang", listRequestLelang);
            startActivity(i);
        }
        else{
            //login user
            databaseReference_user.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Boolean cek = false;
                    for (DataSnapshot ds:dataSnapshot.getChildren()){
                        if(stremail.equals(ds.child("email").getValue().toString())&&strpass.equals(ds.child("password").getValue().toString())){
                            cek=true;
                            FirebaseAuth.getInstance().signInWithEmailAndPassword(stremail,strpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Toast.makeText(Login.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
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
                                    if(stremail.equals(ds2.child("email").getValue().toString())&&strpass.equals(ds2.child("password").getValue().toString())){
                                        FirebaseAuth.getInstance().signInWithEmailAndPassword(stremail,strpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                Toast.makeText(Login.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
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


//            for (int i = 0; i < listUser.size(); i++) {
//                if(stremail.equals(listUser.get(i).email)&& strpass.equals(listUser.get(i).password)){
//                    ctr++;
//                    listUser.get(i).aktif="1";
//                }
//            }
//            if(ctr>0){
//                Intent a = new Intent(Login.this, Home.class);
//                a.putExtra("listUser", listUser);
//                a.putExtra("listToko", listToko);
//                a.putExtra("listBarang", listBarang);
//                a.putExtra("listWishlist", listWishlist);
//                a.putExtra("listRequestLelang", listRequestLelang);
//                a.putExtra("adayanglogin","1");
//                startActivity(a);
//            }
//
//            for (int i = 0; i < listToko.size(); i++) {
//                if(stremail.equals(listToko.get(i).email)&& strpass.equals(listToko.get(i).password)){
//                    listToko.get(i).aktif="1";
//                    ctr2++;
//                }
//            }
//            if(ctr2>0){
//                Intent a = new Intent(Login.this, HomeToko.class);
//                a.putExtra("listUser", listUser);
//                a.putExtra("listToko", listToko);
//                a.putExtra("listWishlist", listWishlist);
//                a.putExtra("listBarang", listBarang);
//                a.putExtra("listRequestLelang", listRequestLelang);
//                startActivity(a);
//            }
//            else if(ctr==0 && ctr2==0){
//                Toast.makeText(this, "Email atau Password salah", Toast.LENGTH_SHORT).show();
//            }
        }
    }
}