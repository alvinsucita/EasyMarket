package com.example.easymarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class NotaActivity extends AppCompatActivity implements NotaProdukFragment.OnFragmentInteractionListener{

    BottomNavigationView bottomNavigationNota;
    Button konfirmasi;
    ArrayList<ClassNota> listClassNota = new ArrayList<>();
    TextView total;
    int cekbarang=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nota);

        konfirmasi = findViewById(R.id.btkonfirmasinota);
        total = findViewById(R.id.tvnotatotalbayar);

        GradientDrawable drawable4 = new GradientDrawable();
        drawable4.setShape(GradientDrawable.RECTANGLE);
        drawable4.setCornerRadius(100);
        drawable4.setColor(Color.BLACK);
        konfirmasi.setBackground(drawable4);

        FirebaseDatabase.getInstance().getReference().child("ClassNota").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Boolean cek = true;
                for (DataSnapshot ds:dataSnapshot.getChildren()){
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
                    listClassNota.add(semua_Class_Nota);
                }
                Intent i = getIntent();
                if(i.hasExtra("dua")){
                    int totalsementara=0;
                    cekbarang=1;
                    for (int j = 0; j < listClassNota.size(); j++) {
                        if(j==listClassNota.size()-2){
                            totalsementara=totalsementara+listClassNota.get(j).total-14000;
                        }
                        else if(j==listClassNota.size()-1){
                            totalsementara=totalsementara+listClassNota.get(j).total-14000;
                        }
                    }
                    String hargaasli = String.format("%,d", totalsementara+14000);
                    total.setText("Rp. "+hargaasli);
                }
                else{
                    int totalsementara=0;
                    for (int j = 0; j < listClassNota.size(); j++) {
                        if(j==listClassNota.size()-1){
                            totalsementara=totalsementara+listClassNota.get(j).total-14000;
                        }
                    }
                    String hargaasli = String.format("%,d", totalsementara+14000);
                    total.setText("Rp. "+hargaasli);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        changeFragment(new NotaProdukFragment(),listClassNota);
        bottomNavigationNota = findViewById(R.id.BottomNavNota);
        bottomNavigationNota.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(menuItem.getItemId()==R.id.produk){
                    changeFragment(new NotaProdukFragment(),listClassNota);
                }else if(menuItem.getItemId()==R.id.pengiriman){
                    changeFragment(new NotaPengirimanFragment(),listClassNota);
                }else if(menuItem.getItemId()==R.id.pembayaran){
                    changeFragment(new NotaPembayaranFragment(),listClassNota);
                }
                return true;
            }
        });
    }

    public void changeFragment(Fragment f, ArrayList<ClassNota> listClassNota){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putSerializable("listClassNota", listClassNota);
        f.setArguments(bundle);
        ft.replace(R.id.FragmentNota, f);
        ft.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void konfirmasi(View view) {
        Intent i = new Intent(NotaActivity.this,HistoryActivity.class);
        startActivity(i);
    }
}