package com.example.easymarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class DetailAlamat extends AppCompatActivity {

    EditText nama,nohp,alamat,kota,kode;
    Spinner pembayaran;
    ArrayList<String> spinnerArray =  new ArrayList<>();
    Button konfirmasi;
    String akunyangbeli="",toko="",idbarang1="",idbarang2="";
    int jumlah=0,jumlah2=0,harga=0,harga2=0;
    ArrayList<ClassNota> listClassNota = new ArrayList<>();
    DatabaseReference databaseReference_nota;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_alamat);
        nama=findViewById(R.id.etNamaPenerima);
        nohp=findViewById(R.id.etNomorHp);
        kota=findViewById(R.id.etKota);
        kode=findViewById(R.id.etKode);
        alamat=findViewById(R.id.etAlamat);
        pembayaran = findViewById(R.id.sppembayaran);
        konfirmasi=findViewById(R.id.btnKonfirmasi);

        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setCornerRadius(100);
        drawable.setColor(Color.WHITE);
        nama.setBackground(drawable);
        nohp.setBackground(drawable);
        kota.setBackground(drawable);
        kode.setBackground(drawable);

        GradientDrawable drawable2 = new GradientDrawable();
        drawable2.setShape(GradientDrawable.RECTANGLE);
        drawable2.setCornerRadius(50);
        drawable2.setColor(Color.WHITE);
        alamat.setBackground(drawable2);

        GradientDrawable drawable3 = new GradientDrawable();
        drawable3.setShape(GradientDrawable.RECTANGLE);
        drawable3.setCornerRadius(100);
        drawable3.setStroke(8, Color.LTGRAY);
        drawable3.setColor(Color.WHITE);
        pembayaran.setBackground(drawable3);

        GradientDrawable drawable4 = new GradientDrawable();
        drawable4.setShape(GradientDrawable.RECTANGLE);
        drawable4.setCornerRadius(100);
        drawable4.setColor(Color.BLACK);
        konfirmasi.setBackground(drawable4);

        spinnerArray.add("COD");
        spinnerArray.add("PayPal");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner sItems = (Spinner) findViewById(R.id.sppembayaran);
        sItems.setAdapter(adapter);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        if (i.hasExtra("idbarang1")) {
            akunyangbeli = i.getStringExtra("akunyangbeli");
            idbarang1 = i.getStringExtra("idbarang1");
            idbarang2 = i.getStringExtra("idbarang2");
            jumlah = i.getIntExtra("jumlah",0);
            jumlah2 = i.getIntExtra("jumlah2",0);
            harga = i.getIntExtra("harga",0);
            harga2 = i.getIntExtra("harga2",0);
            toko = i.getStringExtra("toko");
        }
        else{
            akunyangbeli = i.getStringExtra("akunyangbeli");
            idbarang1 = i.getStringExtra("idbarang");
            jumlah = i.getIntExtra("jumlah",0);
            harga = i.getIntExtra("harga",0);
            toko = i.getStringExtra("toko");
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

    public void toDetailPengiriman(View view) {
        final String strnama = nama.getText().toString();
        String strnohp = nohp.getText().toString();
        final String stralamat = alamat.getText().toString();
        String strkota = kota.getText().toString();
        String strkode = kode.getText().toString();
        final String strjenispembayaran = pembayaran.getSelectedItem().toString();

        if(strnama.equals("")||strnohp.equals("")||stralamat.equals("")||strkota.equals("")||strkode.equals("")){
            Toast.makeText(this, "Isi semua field terlebih dahulu", Toast.LENGTH_SHORT).show();
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

                    if(listClassNota.size()==0){
                        if(jumlah2==0){
                            if(strjenispembayaran.equals("COD")){
                                ClassNota notabaru=new ClassNota("NO0001",toko,idbarang1,strnama,stralamat,strjenispembayaran,"COD",harga,jumlah,14000,harga*jumlah+14000,2);
                                databaseReference_nota = FirebaseDatabase.getInstance().getReference().child("ClassNota");
                                String key=databaseReference_nota.push().getKey();
                                databaseReference_nota.child(key).setValue(notabaru);
                                Intent i = new Intent(DetailAlamat.this,NotaActivity.class);
                                startActivity(i);
                            }
                            else{
                                ClassNota notabaru=new ClassNota("NO0001",toko,idbarang1,strnama,stralamat,strjenispembayaran,"COD",harga,jumlah,14000,harga*jumlah+14000,1);
                                databaseReference_nota = FirebaseDatabase.getInstance().getReference().child("ClassNota");
                                String key=databaseReference_nota.push().getKey();
                                databaseReference_nota.child(key).setValue(notabaru);
                                Intent i = new Intent(DetailAlamat.this,NotaActivity.class);
                                startActivity(i);
                            }
                        }
                        else{
                            if(strjenispembayaran.equals("COD")){
                                ClassNota notabaru=new ClassNota("NO0001",toko,idbarang1,strnama,stralamat,strjenispembayaran,"COD",harga,jumlah,14000,harga*jumlah+14000,2);
                                databaseReference_nota = FirebaseDatabase.getInstance().getReference().child("ClassNota");
                                String key=databaseReference_nota.push().getKey();
                                databaseReference_nota.child(key).setValue(notabaru);

                                ClassNota notabaru2=new ClassNota("NO0001",toko,idbarang2,strnama,stralamat,strjenispembayaran,"COD",harga2,jumlah2,14000,harga2*jumlah2+14000,2);
                                databaseReference_nota = FirebaseDatabase.getInstance().getReference().child("ClassNota");
                                String key2=databaseReference_nota.push().getKey();
                                databaseReference_nota.child(key2).setValue(notabaru2);

                                Intent i = new Intent(DetailAlamat.this,NotaActivity.class);
                                i.putExtra("dua","dua");
                                startActivity(i);
                            }
                            else{
                                ClassNota notabaru=new ClassNota("NO0001",toko,idbarang1,strnama,stralamat,strjenispembayaran,"COD",harga,jumlah,14000,harga*jumlah+14000,1);
                                databaseReference_nota = FirebaseDatabase.getInstance().getReference().child("ClassNota");
                                String key=databaseReference_nota.push().getKey();
                                databaseReference_nota.child(key).setValue(notabaru);

                                ClassNota notabaru2=new ClassNota("NO0001",toko,idbarang2,strnama,stralamat,strjenispembayaran,"COD",harga2,jumlah2,14000,harga2*jumlah2+14000,1);
                                databaseReference_nota = FirebaseDatabase.getInstance().getReference().child("ClassNota");
                                String key2=databaseReference_nota.push().getKey();
                                databaseReference_nota.child(key2).setValue(notabaru2);

                                Intent i = new Intent(DetailAlamat.this,NotaActivity.class);
                                i.putExtra("dua","dua");
                                startActivity(i);
                            }
                        }
                    }
                    else {
                        if(jumlah2==0){
                            if(strjenispembayaran.equals("COD")){
                                ClassNota notabaru=new ClassNota("NO000"+listClassNota.size()+1,toko,idbarang1,strnama,stralamat,strjenispembayaran,"COD",harga,jumlah,14000,harga*jumlah+14000,2);
                                databaseReference_nota = FirebaseDatabase.getInstance().getReference().child("ClassNota");
                                String key=databaseReference_nota.push().getKey();
                                databaseReference_nota.child(key).setValue(notabaru);

                                Intent i = new Intent(DetailAlamat.this,NotaActivity.class);
                                startActivity(i);
                            }
                            else{
                                ClassNota notabaru=new ClassNota("NO000"+listClassNota.size()+1,toko,idbarang1,strnama,stralamat,strjenispembayaran,"COD",harga,jumlah,14000,harga*jumlah+14000,1);
                                databaseReference_nota = FirebaseDatabase.getInstance().getReference().child("ClassNota");
                                String key=databaseReference_nota.push().getKey();
                                databaseReference_nota.child(key).setValue(notabaru);

                                Intent i = new Intent(DetailAlamat.this,NotaActivity.class);
                                startActivity(i);
                            }
                        }
                        else{
                            if(strjenispembayaran.equals("COD")){
                                ClassNota notabaru=new ClassNota("NO000"+listClassNota.size()+1,toko,idbarang1,strnama,stralamat,strjenispembayaran,"COD",harga,jumlah,14000,harga*jumlah+14000,2);
                                databaseReference_nota = FirebaseDatabase.getInstance().getReference().child("ClassNota");
                                String key=databaseReference_nota.push().getKey();
                                databaseReference_nota.child(key).setValue(notabaru);

                                ClassNota notabaru2=new ClassNota("NO000"+listClassNota.size(),toko,idbarang2,strnama,stralamat,strjenispembayaran,"COD",harga2,jumlah2,14000,harga2*jumlah2+14000,2);
                                databaseReference_nota = FirebaseDatabase.getInstance().getReference().child("ClassNota");
                                String key2=databaseReference_nota.push().getKey();
                                databaseReference_nota.child(key2).setValue(notabaru2);

                                Intent i = new Intent(DetailAlamat.this,NotaActivity.class);
                                i.putExtra("dua","dua");
                                startActivity(i);
                            }
                            else{
                                ClassNota notabaru=new ClassNota("NO000"+listClassNota.size()+1,toko,idbarang1,strnama,stralamat,strjenispembayaran,"COD",harga,jumlah,14000,harga*jumlah+14000,1);
                                databaseReference_nota = FirebaseDatabase.getInstance().getReference().child("ClassNota");
                                String key=databaseReference_nota.push().getKey();
                                databaseReference_nota.child(key).setValue(notabaru);

                                ClassNota notabaru2=new ClassNota("NO000"+listClassNota.size(),toko,idbarang2,strnama,stralamat,strjenispembayaran,"COD",harga2,jumlah2,14000,harga2*jumlah2+14000,1);
                                databaseReference_nota = FirebaseDatabase.getInstance().getReference().child("ClassNota");
                                String key2=databaseReference_nota.push().getKey();
                                databaseReference_nota.child(key2).setValue(notabaru2);

                                Intent i = new Intent(DetailAlamat.this,NotaActivity.class);
                                i.putExtra("dua","dua");
                                startActivity(i);
                            }

                        }

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }


    }
}
