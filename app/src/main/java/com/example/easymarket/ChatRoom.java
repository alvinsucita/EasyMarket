package com.example.easymarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ChatRoom extends AppCompatActivity {
    TextView nama;
    Button send;
    EditText pesan;
    RecyclerView rvChat;
    String emailtoko,emailuser;
    ArrayList<ClassToko> listClassToko = new ArrayList<>();
    ArrayList<ClassUser> listClassUser = new ArrayList<>();
    ArrayList<ClassChat> listClassChat = new ArrayList<>();
    AdapterChat adapterChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        nama=findViewById(R.id.tvNamaToko);
        send=findViewById(R.id.btnSend);
        pesan=findViewById(R.id.etIsiChat);
        rvChat=findViewById(R.id.rvChat);

        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setCornerRadius(25);
        drawable.setStroke(8, Color.LTGRAY);
        drawable.setColor(Color.WHITE);
        pesan.setBackground(drawable);

        Intent i = getIntent();
        if(i.hasExtra("user")){
            emailtoko=i.getStringExtra("user");

            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            FirebaseDatabase.getInstance().getReference().child("ClassChat").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot ds:dataSnapshot.getChildren()){
                        if(ds.child("yangdikirim").getValue().toString().equals(emailtoko) && ds.child("yangkirim").getValue().toString().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())||ds.child("yangkirim").getValue().toString().equals(emailtoko) && ds.child("yangdikirim").getValue().toString().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())){
                            ClassChat chat =new ClassChat();
                            chat.setIsi(ds.child("isi").getValue().toString());
                            chat.setWaktu(ds.child("waktu").getValue().toString());
                            chat.setYangdikirim(ds.child("yangdikirim").getValue().toString());
                            chat.setYangkirim(ds.child("yangkirim").getValue().toString());
                            listClassChat.add(chat);
                        }
                    }
                    rvChat.setLayoutManager(new LinearLayoutManager(ChatRoom.this));
                    adapterChat=new AdapterChat(listClassChat);
                    rvChat.setAdapter(adapterChat);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            FirebaseDatabase.getInstance().getReference().child("ClassToko").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Boolean cek = true;
                    for (DataSnapshot ds:dataSnapshot.getChildren()){
                        if(ds.child("email").getValue().toString().equals(emailtoko)){
                            ClassToko semua_Class_Toko =new ClassToko();
                            semua_Class_Toko.setDaerahasal(ds.child("daerahasal").getValue().toString());
                            semua_Class_Toko.setNama(ds.child("nama").getValue().toString());
                            semua_Class_Toko.setEmail(ds.child("email").getValue().toString());
                            semua_Class_Toko.setAktif(ds.child("aktif").getValue().toString());
                            semua_Class_Toko.setRating(Integer.parseInt(ds.child("rating").getValue().toString()));
                            listClassToko.add(semua_Class_Toko);
                        }
                    }
                    nama.setText(listClassToko.get(0).nama);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        else if(i.hasExtra("toko")){
            emailuser=i.getStringExtra("toko");
            FirebaseDatabase.getInstance().getReference().child("ClassChat").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot ds:dataSnapshot.getChildren()){
                        if(ds.child("yangdikirim").getValue().toString().equals(emailuser) && ds.child("yangkirim").getValue().toString().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())||ds.child("yangkirim").getValue().toString().equals(emailuser) && ds.child("yangdikirim").getValue().toString().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())){
                            ClassChat chat =new ClassChat();
                            chat.setIsi(ds.child("isi").getValue().toString());
                            chat.setWaktu(ds.child("waktu").getValue().toString());
                            chat.setYangdikirim(ds.child("yangdikirim").getValue().toString());
                            chat.setYangkirim(ds.child("yangkirim").getValue().toString());
                            listClassChat.add(chat);
                        }
                    }
                    rvChat.setLayoutManager(new LinearLayoutManager(ChatRoom.this));
                    adapterChat=new AdapterChat(listClassChat);
                    rvChat.setAdapter(adapterChat);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            FirebaseDatabase.getInstance().getReference().child("ClassUser").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Boolean cek = true;
                    for (DataSnapshot ds:dataSnapshot.getChildren()){
                        if(ds.child("email").getValue().toString().equals(emailuser)){
                            ClassUser semua_Class_User =new ClassUser();
                            semua_Class_User.setNama(ds.child("nama").getValue().toString());
                            semua_Class_User.setEmail(ds.child("email").getValue().toString());
                            listClassUser.add(semua_Class_User);
                        }
                    }
                    nama.setText(listClassUser.get(0).nama);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date a = new Date();
                SimpleDateFormat time = new SimpleDateFormat("hh:mm aa");
                Calendar now = Calendar.getInstance();
                String waktu =now.get(Calendar.DAY_OF_MONTH)+"/01/"+now.get(Calendar.YEAR)+" - "+time.format(a);

                Intent i = getIntent();
                if(i.hasExtra("user")){
                    ClassChat chat = new ClassChat(pesan.getText().toString(),FirebaseAuth.getInstance().getCurrentUser().getEmail(),emailtoko,waktu);
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("ClassChat");
                    final String key=databaseReference.push().getKey();
                    databaseReference.child(key).setValue(chat);

                    listClassChat.clear();
                    FirebaseDatabase.getInstance().getReference().child("ClassChat").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot ds:dataSnapshot.getChildren()){
                                if(ds.child("yangdikirim").getValue().toString().equals(emailtoko) && ds.child("yangkirim").getValue().toString().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())||ds.child("yangkirim").getValue().toString().equals(emailtoko) && ds.child("yangdikirim").getValue().toString().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())){
                                    ClassChat chat =new ClassChat();
                                    chat.setIsi(ds.child("isi").getValue().toString());
                                    chat.setWaktu(ds.child("waktu").getValue().toString());
                                    chat.setYangdikirim(ds.child("yangdikirim").getValue().toString());
                                    chat.setYangkirim(ds.child("yangkirim").getValue().toString());
                                    listClassChat.add(chat);
                                }
                            }
                            rvChat.setLayoutManager(new LinearLayoutManager(ChatRoom.this));
                            adapterChat=new AdapterChat(listClassChat);
                            rvChat.setAdapter(adapterChat);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                else if(i.hasExtra("toko")){
                    ClassChat chat = new ClassChat(pesan.getText().toString(),FirebaseAuth.getInstance().getCurrentUser().getEmail(),emailuser,waktu);
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("ClassChat");
                    final String key=databaseReference.push().getKey();
                    databaseReference.child(key).setValue(chat);

                    listClassChat.clear();
                    FirebaseDatabase.getInstance().getReference().child("ClassChat").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot ds:dataSnapshot.getChildren()){
                                if(ds.child("yangdikirim").getValue().toString().equals(emailuser) && ds.child("yangkirim").getValue().toString().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())||ds.child("yangkirim").getValue().toString().equals(emailuser) && ds.child("yangdikirim").getValue().toString().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())){
                                    ClassChat chat =new ClassChat();
                                    chat.setIsi(ds.child("isi").getValue().toString());
                                    chat.setWaktu(ds.child("waktu").getValue().toString());
                                    chat.setYangdikirim(ds.child("yangdikirim").getValue().toString());
                                    chat.setYangkirim(ds.child("yangkirim").getValue().toString());
                                    listClassChat.add(chat);
                                }
                            }
                            rvChat.setLayoutManager(new LinearLayoutManager(ChatRoom.this));
                            adapterChat=new AdapterChat(listClassChat);
                            rvChat.setAdapter(adapterChat);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }

                pesan.setText("");
            }
        });
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
