package com.example.easymarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Register extends AppCompatActivity {
    Button register;
    EditText email,password,conpassword;
    String stremail,strpassword,strconpassword;
    RadioButton toko,user;
    RadioGroup rgJenis;
    String tiperegister="";
    DatabaseReference databaseReference_user,databaseReference_toko;
    ClassUser userbaru;
    ClassToko tokobaru;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register=findViewById(R.id.btnRegister);
        email=findViewById(R.id.etEmail);
        password=findViewById(R.id.etPassword);
        conpassword=findViewById(R.id.etKonfirmasiPassword);
        toko=findViewById(R.id.rbToko);
        user=findViewById(R.id.rbUser);
        rgJenis=findViewById(R.id.jenisuser);

        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setCornerRadius(100);
        drawable.setColor(Color.BLACK);
        register.setBackground(drawable);

        GradientDrawable drawable3 = new GradientDrawable();
        drawable3.setShape(GradientDrawable.RECTANGLE);
        drawable3.setCornerRadius(100);
        drawable3.setColor(Color.WHITE);
        password.setBackground(drawable3);

        GradientDrawable drawable4 = new GradientDrawable();
        drawable4.setShape(GradientDrawable.RECTANGLE);
        drawable4.setCornerRadius(100);
        drawable4.setColor(Color.WHITE);
        email.setBackground(drawable4);

        GradientDrawable drawable7 = new GradientDrawable();
        drawable7.setShape(GradientDrawable.RECTANGLE);
        drawable7.setCornerRadius(100);
        drawable7.setColor(Color.WHITE);
        rgJenis.setBackground(drawable7);

        GradientDrawable drawable5 = new GradientDrawable();
        drawable5.setShape(GradientDrawable.RECTANGLE);
        drawable5.setCornerRadius(100);
        drawable5.setColor(Color.WHITE);
        conpassword.setBackground(drawable5);
    }

    public void register(View view) {
        stremail=email.getText().toString();
        strpassword=password.getText().toString();
        strconpassword=conpassword.getText().toString();
        int panjangpassword=strpassword.length();

        if(stremail.equals("")|| strpassword.equals("") || strconpassword.equals("")||tiperegister.equals("")){
            Toast.makeText(this, "Isi semua field terlebih dahulu", Toast.LENGTH_SHORT).show();
        }
        else if(panjangpassword<8 || panjangpassword>16){
            Toast.makeText(this, "Panjang password 8-16 karakter", Toast.LENGTH_SHORT).show();
        }
        else{
            if(!strpassword.equals(strconpassword)){
                Toast.makeText(this, "Password dan konfirmasi password harus sama", Toast.LENGTH_SHORT).show();
            }
            else{
                Intent i = new Intent(Register.this,Login.class);
                if(tiperegister.equals("toko")){
                    tokobaru = new ClassToko();
                    tokobaru.setAktif("1");
                    tokobaru.setEmail(stremail);
                    tokobaru.setNama(stremail);
                    tokobaru.setPassword(strpassword);




                    databaseReference_toko = FirebaseDatabase.getInstance().getReference().child("ClassToko");
                    databaseReference_user = FirebaseDatabase.getInstance().getReference().child("ClassUser");
                    databaseReference_user.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Boolean cek = true;
                            for (DataSnapshot ds:dataSnapshot.getChildren()){
                                if(stremail.equals(ds.child("email").getValue().toString())){
                                    Toast.makeText(Register.this, "Email sudah terpakai", Toast.LENGTH_SHORT).show();
                                    cek=false;
                                }
                            }
                            if(cek==true){
                                databaseReference_toko.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        Boolean cek = true;
                                        for (DataSnapshot ds:dataSnapshot.getChildren()){
                                            if(stremail.equals(ds.child("email").getValue().toString())){
                                                Toast.makeText(Register.this, "Email sudah terpakai", Toast.LENGTH_SHORT).show();
                                                cek=false;
                                            }
                                        }
                                        if(cek==true){
                                            FirebaseAuth.getInstance().createUserWithEmailAndPassword(stremail,strpassword).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                                @Override
                                                public void onSuccess(AuthResult authResult) {
                                                    FirebaseAuth.getInstance().signInWithEmailAndPassword(stremail,strpassword).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                                        @Override
                                                        public void onSuccess(AuthResult authResult) {
                                                            tokobaru.setFirebaseUID(FirebaseAuth.getInstance().getCurrentUser().getUid());
                                                            databaseReference_toko.push().setValue(tokobaru).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                @Override
                                                                public void onComplete(@NonNull Task<Void> task) {
                                                                    if(task.isSuccessful()) {
                                                                        Toast.makeText(Register.this, "Toko berhasil didaftarkan", Toast.LENGTH_SHORT).show();
                                                                        Intent i = new Intent(Register.this,HomeToko.class);
                                                                        startActivity(i);
                                                                        FirebaseAuth.getInstance().signOut();
                                                                        FirebaseAuth.getInstance().signInWithEmailAndPassword("guest@guest.com","guest123");
                                                                    }
                                                                }
                                                            });
                                                        }
                                                    });
                                                }
                                            });
                                        }
                                        else{
                                            Toast.makeText(Register.this, "Email sudah terpakai", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                            }
                            else{
                                Toast.makeText(Register.this, "Email sudah terpakai", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                else if(tiperegister.equals("user")){
                    userbaru = new ClassUser();
                    userbaru.setNama(stremail);
                    userbaru.setAktif("1");
                    userbaru.setDaerahasal("");
                    userbaru.setEmail(stremail);
                    userbaru.setGender("");
                    userbaru.setPassword(strpassword);
                    userbaru.setUmur("");

                    databaseReference_toko = FirebaseDatabase.getInstance().getReference().child("ClassToko");
                    databaseReference_user = FirebaseDatabase.getInstance().getReference().child("ClassUser");
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(stremail,strpassword).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            FirebaseAuth.getInstance().signInWithEmailAndPassword(stremail,strpassword).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    databaseReference_user.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            Boolean cek = true;
                                            for (DataSnapshot ds:dataSnapshot.getChildren()){
                                                if(stremail.equals(ds.child("email").getValue().toString())){
                                                    Toast.makeText(Register.this, "Email sudah terpakai", Toast.LENGTH_SHORT).show();
                                                    cek=false;
                                                }
                                            }
                                            if(cek==true){
                                                databaseReference_toko.addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                        Boolean cek = true;
                                                        for (DataSnapshot ds:dataSnapshot.getChildren()){
                                                            if(stremail.equals(ds.child("email").getValue().toString())){
                                                                Toast.makeText(Register.this, "Email sudah terpakai", Toast.LENGTH_SHORT).show();
                                                                cek=false;
                                                            }
                                                        }
                                                        if(cek==true){
                                                            userbaru.setFirebaseUID(FirebaseAuth.getInstance().getCurrentUser().getUid());
                                                            databaseReference_user.push().setValue(userbaru).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                @Override
                                                                public void onComplete(@NonNull Task<Void> task) {
                                                                    if(task.isSuccessful()) {
                                                                        Toast.makeText(Register.this, "User berhasil didaftarkan", Toast.LENGTH_SHORT).show();
                                                                        Intent i = new Intent(Register.this,Login.class);
                                                                        startActivity(i);
                                                                        FirebaseAuth.getInstance().signOut();
                                                                    }
                                                                }
                                                            });
                                                        }
                                                        else{
                                                            Toast.makeText(Register.this, "Email sudah terpakai", Toast.LENGTH_SHORT).show();
                                                        }
                                                    }

                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                                    }
                                                });
                                            }
                                            else{
                                                Toast.makeText(Register.this, "Email sudah terpakai", Toast.LENGTH_SHORT).show();
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                }
                            });
                        }
                    });

                }
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

    public void pilihtoko(View view) {
        tiperegister="toko";
    }

    public void pilihuser(View view) {
        tiperegister="user";
    }

    public void toLogin(View view) {
        Intent i = new Intent(Register.this, Login.class);
        startActivity(i);
    }
}