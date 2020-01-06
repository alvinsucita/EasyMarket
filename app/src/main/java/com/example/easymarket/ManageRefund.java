package com.example.easymarket;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ManageRefund extends AppCompatActivity {

    RecyclerView rvrefund;
    AdapterRefund adapterrefund;
    ArrayList<ClassNota> listClassNota = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_refund);

        rvrefund = findViewById(R.id.rvRefund);

        GradientDrawable drawable3 = new GradientDrawable();
        drawable3.setShape(GradientDrawable.RECTANGLE);
        drawable3.setCornerRadius(25);
        drawable3.setStroke(8, Color.LTGRAY);
        drawable3.setColor(Color.WHITE);
        rvrefund.setBackground(drawable3);

        FirebaseDatabase.getInstance().getReference().child("ClassNota").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Boolean cek = true;
                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    if(Integer.parseInt(ds.child("posisi").getValue().toString())==9){
                        ClassNota semua_Class_Nota =new ClassNota();
                        semua_Class_Nota.setIdnota((ds.child("idnota").getValue().toString()));
                        semua_Class_Nota.setNamatoko((ds.child("namatoko").getValue().toString()));
                        semua_Class_Nota.setIdbarang((ds.child("idbarang").getValue().toString()));
                        semua_Class_Nota.setNamauser(ds.child("namauser").getValue().toString());
                        semua_Class_Nota.setAlamat((ds.child("alamat").getValue().toString()));
                        semua_Class_Nota.setPembayaran((ds.child("pembayaran").getValue().toString()));
                        semua_Class_Nota.setJenispengiriman((ds.child("jenispengiriman").getValue().toString()));
                        semua_Class_Nota.setHargabarang(Integer.parseInt(ds.child("hargabarang").getValue().toString()));
                        semua_Class_Nota.setJumlahbarang(Integer.parseInt(ds.child("jumlahbarang").getValue().toString()));
                        semua_Class_Nota.setHargapengiriman(Integer.parseInt(ds.child("hargapengiriman").getValue().toString()));
                        semua_Class_Nota.setTotal(Integer.parseInt(ds.child("total").getValue().toString()));
                        semua_Class_Nota.setPosisi(Integer.parseInt(ds.child("posisi").getValue().toString()));
                        listClassNota.add(semua_Class_Nota);
                    }
                }
                rvrefund.setLayoutManager(new LinearLayoutManager(ManageRefund.this));
                adapterrefund=new AdapterRefund(listClassNota, new RVClickListener() {
                    @Override
                    public void recyclerViewListBarangClick(View v, int posisi) {
                        Intent i = new Intent(ManageRefund.this,NotaActivity.class);
                        i.putExtra("liat",listClassNota);
                        i.putExtra("indeks",posisi);
                        i.putExtra("posisi",9);
                        startActivity(i);
                    }
                });
                rvrefund.setAdapter(adapterrefund);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
