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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class NotaActivity extends AppCompatActivity implements NotaProdukFragment.OnFragmentInteractionListener{

    BottomNavigationView bottomNavigationNota;
    Button konfirmasi,cancel,bayar;
    ArrayList<ClassNota> listClassNota = new ArrayList<>();
    ArrayList<ClassBarang> listClassBarang = new ArrayList<>();
    ArrayList<ClassBarang> listClassBarangFilter = new ArrayList<>();
    TextView total;
    int cekbarang=0,cekliat=0,cekada=0,indeks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nota);

        konfirmasi = findViewById(R.id.btkonfirmasinota);
        bayar=findViewById(R.id.btnBayar);
        cancel=findViewById(R.id.btnCancel);
        total = findViewById(R.id.tvnotatotalbayar);

        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setCornerRadius(100);
        drawable.setColor(Color.BLACK);
        konfirmasi.setBackground(drawable);

        GradientDrawable drawable2 = new GradientDrawable();
        drawable2.setShape(GradientDrawable.RECTANGLE);
        drawable2.setCornerRadius(100);
        drawable2.setColor(Color.BLACK);
        bayar.setBackground(drawable2);

        GradientDrawable drawable3 = new GradientDrawable();
        drawable3.setShape(GradientDrawable.RECTANGLE);
        drawable3.setCornerRadius(100);
        drawable3.setColor(Color.BLACK);
        cancel.setBackground(drawable3);

        Intent i = getIntent();
        if(i.hasExtra("liat")){
            cekada=1;
            indeks=i.getIntExtra("indeks",0);
            cekliat=i.getIntExtra("posisi",0);
            listClassNota = (ArrayList<ClassNota>) i.getSerializableExtra("liat");
            int totalsementara=0;

            totalsementara=totalsementara+listClassNota.get(indeks).total-14000;

            String hargaasli = String.format("%,d", totalsementara+14000);
            total.setText("Rp. "+hargaasli);

            if(cekliat==1){
                konfirmasi.setVisibility(View.INVISIBLE);
            }
            else if(cekliat==2){
                bayar.setText("Refund");
                cancel.setText("Kembali");
                konfirmasi.setVisibility(View.INVISIBLE);
            }
            else if(cekliat==5){
                bayar.setText("Konfirmasi");
                konfirmasi.setVisibility(View.INVISIBLE);
            }
            else{
                cancel.setVisibility(View.INVISIBLE);
                bayar.setVisibility(View.INVISIBLE);
            }
        }
        else{
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
                        semua_Class_Nota.setPosisi(Integer.parseInt(ds.child("posisi").getValue().toString()));
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
                    cancel.setVisibility(View.INVISIBLE);
                    bayar.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

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
        if(cekliat==9){
            final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("ClassNota");
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Boolean cek = true;
                    for (DataSnapshot ds:dataSnapshot.getChildren()){
                        if(listClassNota.get(indeks).getIdnota().equals(ds.child("idnota").getValue().toString())){
                            ClassNota updatenota = new ClassNota();
                            updatenota.setIdnota(ds.child("idnota").getValue().toString());
                            updatenota.setNamatoko(ds.child("namatoko").getValue().toString());
                            updatenota.setIdbarang(ds.child("idbarang").getValue().toString());
                            updatenota.setPosisi(4);
                            updatenota.setNamauser(ds.child("namauser").getValue().toString());
                            updatenota.setAlamat(ds.child("alamat").getValue().toString());
                            updatenota.setPembayaran(ds.child("pembayaran").getValue().toString());
                            updatenota.setJenispengiriman(ds.child("jenispengiriman").getValue().toString());
                            updatenota.setHargabarang(Integer.parseInt(ds.child("hargabarang").getValue().toString()));
                            updatenota.setJumlahbarang(Integer.parseInt(ds.child("jumlahbarang").getValue().toString()));
                            updatenota.setHargapengiriman(Integer.parseInt(ds.child("hargapengiriman").getValue().toString()));
                            updatenota.setTotal(Integer.parseInt(ds.child("total").getValue().toString()));

                            databaseReference.child(ds.getKey()).setValue(updatenota).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(NotaActivity.this, "Transaksi ini telah berhasil anda refund", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                    Intent i = new Intent(NotaActivity.this,HomeAdmin.class);
                    startActivity(i);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        else{
            Intent i = new Intent(NotaActivity.this,HistoryActivity.class);
            startActivity(i);
        }
    }

    public void bayar(View view) {
        if(cekliat==5){
            FirebaseDatabase.getInstance().getReference().child("ClassBarang").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Boolean cek = true;
                    for (DataSnapshot ds:dataSnapshot.getChildren()){
                            ClassBarang semua_Class_barang =new ClassBarang();
                            semua_Class_barang.setDeskripsi(ds.child("deskripsi").getValue().toString());
                            semua_Class_barang.setDibeli(Integer.parseInt(ds.child("dibeli").getValue().toString()));
                            semua_Class_barang.setDilihat(Integer.parseInt(ds.child("dilihat").getValue().toString()));
                            semua_Class_barang.setHarga(Integer.parseInt(ds.child("harga").getValue().toString()));
                            semua_Class_barang.setIdbarang(ds.child("idbarang").getValue().toString());
                            semua_Class_barang.setKategori(ds.child("kategori").getValue().toString());
                            semua_Class_barang.setLikes(Integer.parseInt(ds.child("likes").getValue().toString()));
                            semua_Class_barang.setNamabarang(ds.child("namabarang").getValue().toString());
                            semua_Class_barang.setNamatoko(ds.child("namatoko").getValue().toString());
                            semua_Class_barang.setStok(Integer.parseInt(ds.child("stok").getValue().toString()));
                            listClassBarang.add(semua_Class_barang);
                    }
                    for (int i = 0; i < listClassBarang.size(); i++) {
                        for (int j = 0; j < listClassNota.size(); j++) {
                            if(listClassBarang.get(i).idbarang.equals(listClassNota.get(j).idbarang)){
                                listClassBarangFilter.add(listClassBarang.get(i));
                            }
                        }
                    }

                    final int totalstok = listClassBarang.get(0).stok-listClassNota.get(indeks).jumlahbarang;
                    if(totalstok>=0){
                        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("ClassBarang");
                        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                Boolean cek = true;
                                for (DataSnapshot ds:dataSnapshot.getChildren()){
                                    if(ds.child("namatoko").getValue().toString().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())){
                                        ClassBarang updatebarang = new ClassBarang();
                                        updatebarang.setNamabarang(ds.child("namabarang").getValue().toString());
                                        updatebarang.setStok(totalstok);
                                        updatebarang.setNamatoko(ds.child("namatoko").getValue().toString());
                                        updatebarang.setLikes(Integer.parseInt(ds.child("likes").getValue().toString()));
                                        updatebarang.setIdbarang(ds.child("idbarang").getValue().toString());
                                        updatebarang.setDeskripsi(ds.child("deskripsi").getValue().toString());
                                        updatebarang.setKategori(ds.child("kategori").getValue().toString());
                                        updatebarang.setHarga(Integer.parseInt(ds.child("harga").getValue().toString()));
                                        updatebarang.setDilihat(Integer.parseInt(ds.child("dilihat").getValue().toString()));
                                        updatebarang.setDibeli(Integer.parseInt(ds.child("dibeli").getValue().toString()));
                                        databaseReference.child(ds.getKey()).setValue(updatebarang).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Toast.makeText(NotaActivity.this, "Transaksi berhasil dikonfirmasi", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    }
                                }
                                final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("ClassNota");
                                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        Boolean cek = true;
                                        for (DataSnapshot ds:dataSnapshot.getChildren()){
                                            if(listClassNota.get(indeks).getIdnota().equals(ds.child("idnota").getValue().toString())){
                                                ClassNota updatenota = new ClassNota();
                                                updatenota.setIdnota(ds.child("idnota").getValue().toString());
                                                updatenota.setNamatoko(ds.child("namatoko").getValue().toString());
                                                updatenota.setIdbarang(ds.child("idbarang").getValue().toString());
                                                updatenota.setPosisi(3);
                                                updatenota.setNamauser(ds.child("namauser").getValue().toString());
                                                updatenota.setAlamat(ds.child("alamat").getValue().toString());
                                                updatenota.setPembayaran(ds.child("pembayaran").getValue().toString());
                                                updatenota.setJenispengiriman(ds.child("jenispengiriman").getValue().toString());
                                                updatenota.setHargabarang(Integer.parseInt(ds.child("hargabarang").getValue().toString()));
                                                updatenota.setJumlahbarang(Integer.parseInt(ds.child("jumlahbarang").getValue().toString()));
                                                updatenota.setHargapengiriman(Integer.parseInt(ds.child("hargapengiriman").getValue().toString()));
                                                updatenota.setTotal(Integer.parseInt(ds.child("total").getValue().toString()));

                                                databaseReference.child(ds.getKey()).setValue(updatenota).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        Toast.makeText(NotaActivity.this, "Transaksi ini telah anda batalkan", Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                            }
                                        }
                                        if(cekliat==5){
                                            Intent i = new Intent(NotaActivity.this,HomeToko.class);
                                            startActivity(i);
                                        }
                                        else{
                                            Intent i = new Intent(NotaActivity.this,HistoryActivity.class);
                                            startActivity(i);
                                        }
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
                    else{
                        Toast.makeText(NotaActivity.this, "stok barang anda tidak cukup", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        else if(cekliat==2){
            final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("ClassNota");
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Boolean cek = true;
                    for (DataSnapshot ds:dataSnapshot.getChildren()){
                        if(listClassNota.get(indeks).getIdnota().equals(ds.child("idnota").getValue().toString())){
                            ClassNota updatenota = new ClassNota();
                            updatenota.setIdnota(ds.child("idnota").getValue().toString());
                            updatenota.setNamatoko(ds.child("namatoko").getValue().toString());
                            updatenota.setIdbarang(ds.child("idbarang").getValue().toString());
                            updatenota.setPosisi(9);
                            updatenota.setNamauser(ds.child("namauser").getValue().toString());
                            updatenota.setAlamat(ds.child("alamat").getValue().toString());
                            updatenota.setPembayaran(ds.child("pembayaran").getValue().toString());
                            updatenota.setJenispengiriman(ds.child("jenispengiriman").getValue().toString());
                            updatenota.setHargabarang(Integer.parseInt(ds.child("hargabarang").getValue().toString()));
                            updatenota.setJumlahbarang(Integer.parseInt(ds.child("jumlahbarang").getValue().toString()));
                            updatenota.setHargapengiriman(Integer.parseInt(ds.child("hargapengiriman").getValue().toString()));
                            updatenota.setTotal(Integer.parseInt(ds.child("total").getValue().toString()));

                            databaseReference.child(ds.getKey()).setValue(updatenota).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(NotaActivity.this, "Transaksi ini telah anda minta untuk refund, tunggu konfirmasi selanjutnya dari admin", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                    Intent i = new Intent(NotaActivity.this,HistoryActivity.class);
                    startActivity(i);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        else{
            Intent i = new Intent(NotaActivity.this,ActivityPembayaran.class);
            i.putExtra("nota",listClassNota);
            startActivity(i);
        }
    }

    public void cancel(View view) {
        if(cekliat==2){
            Intent i = new Intent(NotaActivity.this,HistoryActivity.class);
            startActivity(i);
        }
        else{
            final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("ClassNota");
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Boolean cek = true;
                    for (DataSnapshot ds:dataSnapshot.getChildren()){
                        if(listClassNota.get(indeks).getIdnota().equals(ds.child("idnota").getValue().toString())){
                            ClassNota updatenota = new ClassNota();
                            updatenota.setIdnota(ds.child("idnota").getValue().toString());
                            updatenota.setNamatoko(ds.child("namatoko").getValue().toString());
                            updatenota.setIdbarang(ds.child("idbarang").getValue().toString());
                            updatenota.setPosisi(4);
                            updatenota.setNamauser(ds.child("namauser").getValue().toString());
                            updatenota.setAlamat(ds.child("alamat").getValue().toString());
                            updatenota.setPembayaran(ds.child("pembayaran").getValue().toString());
                            updatenota.setJenispengiriman(ds.child("jenispengiriman").getValue().toString());
                            updatenota.setHargabarang(Integer.parseInt(ds.child("hargabarang").getValue().toString()));
                            updatenota.setJumlahbarang(Integer.parseInt(ds.child("jumlahbarang").getValue().toString()));
                            updatenota.setHargapengiriman(Integer.parseInt(ds.child("hargapengiriman").getValue().toString()));
                            updatenota.setTotal(Integer.parseInt(ds.child("total").getValue().toString()));

                            databaseReference.child(ds.getKey()).setValue(updatenota).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(NotaActivity.this, "Transaksi ini telah anda batalkan", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                    if(cekliat==5){
                        Intent i = new Intent(NotaActivity.this,HomeToko.class);
                        startActivity(i);
                    }
                    else{
                        Intent i = new Intent(NotaActivity.this,HistoryActivity.class);
                        startActivity(i);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }
}