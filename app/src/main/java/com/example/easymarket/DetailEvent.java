package com.example.easymarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DetailEvent extends AppCompatActivity {

    TextView deskripsi,judul;
    ArrayList<ClassEvent> listClassEvent = new ArrayList<>();
    int indeks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_event);

        deskripsi = findViewById(R.id.tvDeskripsiEvent);
        judul = findViewById(R.id.tvJudulEvent);

        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setCornerRadius(50);
        drawable.setStroke(8, Color.LTGRAY);
        drawable.setColor(Color.WHITE);
        deskripsi.setBackground(drawable);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        indeks=i.getIntExtra("posisi",0);

        FirebaseDatabase.getInstance().getReference().child("ClassEvent").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Boolean cek = true;
                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    ClassEvent semua_Class_Event =new ClassEvent();
                    semua_Class_Event.setJudul((ds.child("judul").getValue().toString()));
                    semua_Class_Event.setDesc((ds.child("desc").getValue().toString()));
                    listClassEvent.add(semua_Class_Event);
                }
                deskripsi.setText(listClassEvent.get(indeks).desc);
                judul.setText(listClassEvent.get(indeks).judul);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home){
            Intent i = new Intent(DetailEvent.this,Home.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
}
