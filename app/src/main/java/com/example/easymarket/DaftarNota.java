package com.example.easymarket;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DaftarNota extends AppCompatActivity {

    ListView listView;
    ArrayList<ClassNota> listClassNota = new ArrayList<>();

    ArrayList<String> test = new ArrayList<>();
    String[] user;
    String a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_nota);
        listView = findViewById(R.id.lvnota);

        FirebaseDatabase.getInstance().getReference().child("ClassNota").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    ClassNota semua_Class_nota = new ClassNota();
                    semua_Class_nota.setIdnota(ds.child("idnota").getValue().toString());
                    semua_Class_nota.setNamatoko(ds.child("namatoko").getValue().toString());
                    semua_Class_nota.setIdbarang(ds.child("idbarang").getValue().toString());
                    semua_Class_nota.setNamauser(ds.child("namauser").getValue().toString());
                    semua_Class_nota.setAlamat(ds.child("alamat").getValue().toString());
                    semua_Class_nota.setPembayaran(ds.child("pembayaran").getValue().toString());
                    semua_Class_nota.setJenispengiriman(ds.child("jenispengiriman").getValue().toString());
//                    semua_Class_nota.setHargabarang(Integer.valueOf(ds.child("hargabarang").getValue().toString());
//                    semua_Class_nota.setJumlahbarang(Integer.valueOf(ds.child("jumlahbarang").getValue().toString());
//                    semua_Class_nota.setHargapengiriman(Integer.valueOf(ds.child("hargapengiriman").getValue().toString());
//                    semua_Class_nota.setTotal(Integer.valueOf(ds.child("total").getValue().toString()));
                    listClassNota.add(semua_Class_nota);
                }
                for(int a = 0; a< listClassNota.size(); a++){
                    test.add(listClassNota.get(a).getIdnota());
                }
                user = new String[listClassNota.size()];
                user = test.toArray(user);
                AdapterMenuAdmin ama = new AdapterMenuAdmin(DaftarNota.this, user);
                listView.setAdapter(ama);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        Toast.makeText(ManageUsers.this, position + "", Toast.LENGTH_LONG).show();

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
