package com.example.easymarket;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ManageCloseLelang extends AppCompatActivity {

    ListView listView;
    ArrayList<ClassLelang> listClassLelang = new ArrayList<>();
    ArrayList<ClassBarang> listClassBarang = new ArrayList<>();
    ArrayList<ClassBarang> listFilterClassBarang = new ArrayList<>();
    ArrayList<ClassLelang> listFilterClassLelang = new ArrayList<>();

    ArrayList<String> test = new ArrayList<>();
    String[] user;
    String a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_close_lelang);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listView = findViewById(R.id.lvclose);
        FirebaseDatabase.getInstance().getReference().child("ClassLelang").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    ClassLelang semua_Class_lelang = new ClassLelang();
                    semua_Class_lelang.setIdbarang(ds.child("idbarang").getValue().toString());
                    semua_Class_lelang.setHarganormal(Integer.valueOf(ds.child("harganormal").getValue().toString()));
                    semua_Class_lelang.setHargatertinggi(Integer.valueOf(ds.child("hargatertinggi").getValue().toString()));
                    semua_Class_lelang.setHargaawal(Integer.valueOf(ds.child("hargaawal").getValue().toString()));
                    semua_Class_lelang.setNamabidder(ds.child("namabidder").getValue().toString());
                    listClassLelang.add(semua_Class_lelang);
                }
                FirebaseDatabase.getInstance().getReference().child("ClassBarang").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds:dataSnapshot.getChildren()){
                            ClassBarang semua_Class_barang =new ClassBarang();
                            semua_Class_barang.setIdbarang(ds.child("idbarang").getValue().toString());
                            semua_Class_barang.setNamabarang(ds.child("namabarang").getValue().toString());
                            listClassBarang.add(semua_Class_barang);
                        }
                        for (int i = 0; i < listClassLelang.size(); i++) {
                            for (int j = 0; j < listClassBarang.size(); j++) {
                                if(listClassBarang.get(j).idbarang.equals(listClassLelang.get(i).idbarang)){
                                    listFilterClassBarang.add(listClassBarang.get(j));
                                    listFilterClassLelang.add(listClassLelang.get(i));
                                }
                            }
                        }
                        for(int i = 0; i < listFilterClassBarang.size(); i++){
                            test.add(listFilterClassBarang.get(i).getNamabarang());
                        }
                        user = new String[listFilterClassBarang.size()];
                        user = test.toArray(user);
                        AdapterMenuAdmin ama = new AdapterMenuAdmin(ManageCloseLelang.this, user);
                        listView.setAdapter(ama);
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                TextView tv = findViewById(R.id.layoutjudul);
                                a = tv.getText().toString();
                                AlertDialog.Builder builder = new AlertDialog.Builder(ManageCloseLelang.this);
                                builder.setMessage("Apakah anda yakin untuk menutup lelang ini?" +
                                        "\nBid Tertinggi: Rp " + listFilterClassLelang.get(0).getHargatertinggi() + ",-" +
                                        "\nNama Bidder: Rp " + listFilterClassLelang.get(0).getNamabidder()
                                );
                                builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("ClassLelang");
                                        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                for (DataSnapshot ds:dataSnapshot.getChildren()){
                                                    if(ds.child("idbarang").getValue().toString().equals(a)){
                                                        ds.getRef().removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                Toast.makeText(ManageCloseLelang.this, "berhasil close", Toast.LENGTH_SHORT).show();
                                                                final DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("ClassRequestLelang");
                                                                db.addListenerForSingleValueEvent(new ValueEventListener() {
                                                                    @Override
                                                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                        for (DataSnapshot dss:dataSnapshot.getChildren()){
                                                                            if(dss.child("idbarang").getValue().toString().equals(a)){
                                                                                ClassRequestLelang updatelelang = new ClassRequestLelang();
                                                                                if(dss.child("masuklelang").getValue().toString().equals("0")) {
                                                                                    updatelelang.setMasuklelang(100);
                                                                                    updatelelang.setIdbarang(dss.child("idbarang").getValue().toString());
                                                                                }
                                                                            }
                                                                        }
                                                                    }

                                                                    @Override
                                                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                                                    }
                                                                });
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
                                });
                                builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
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
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
