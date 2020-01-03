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
    TextView total;
    int cekbarang=0,cekliat=0,cekada=0;

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
            cekliat=i.getIntExtra("posisi",0);
            listClassNota = (ArrayList<ClassNota>) i.getSerializableExtra("liat");
            int totalsementara=0;
            for (int j = 0; j < listClassNota.size(); j++) {
                if(j==listClassNota.size()-1){
                    totalsementara=totalsementara+listClassNota.get(j).total-14000;
                }
            }
            String hargaasli = String.format("%,d", totalsementara+14000);
            total.setText("Rp. "+hargaasli);

            if(cekliat==1){
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
        Intent i = new Intent(NotaActivity.this,HistoryActivity.class);
        startActivity(i);
    }

    public void bayar(View view) {
        Intent i = new Intent(NotaActivity.this,ActivityPembayaran.class);
        i.putExtra("nota",listClassNota);
        startActivity(i);
    }

    public void cancel(View view) {
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("ClassNota");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Boolean cek = true;
                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    if(listClassNota.get(0).getIdnota().equals(ds.child("idnota").getValue().toString())){
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
                Intent i = new Intent(NotaActivity.this,HistoryActivity.class);
                startActivity(i);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}