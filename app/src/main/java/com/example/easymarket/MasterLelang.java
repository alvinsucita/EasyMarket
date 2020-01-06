package com.example.easymarket;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
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

public class MasterLelang extends AppCompatActivity {

    ListView listView;
    ArrayList<ClassRequestLelang> listClassReq = new ArrayList<>();
    DatabaseReference dbreftemp;

    ArrayList<String> test = new ArrayList<>();
    String[] user;
    String a;
    Button refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_lelang);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listView = findViewById(R.id.lvlelang);
        refresh = findViewById(R.id.btnInputLelang);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance().getReference().child("ClassRequestLelang").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds:dataSnapshot.getChildren()){
                            if(ds.child("masuklelang").getValue().toString().equals("0")){
                                ClassRequestLelang semua_Class_req = new ClassRequestLelang();
                                semua_Class_req.setIdbarang(ds.child("idbarang").getValue().toString());
                                semua_Class_req.setMasuklelang(Integer.parseInt(ds.child("masuklelang").getValue().toString()));
                                listClassReq.add(semua_Class_req);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });

                FirebaseDatabase.getInstance().getReference().child("ClassBarang").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds:dataSnapshot.getChildren()){
                            for(int i = 0; i < listClassReq.size(); i++){
                                if(ds.child("idbarang").getValue().toString().equals(listClassReq.get(i).getIdbarang())){
                                    Toast.makeText(MasterLelang.this, "Barang berhasil dikonfirmasi dan masuk ke lelang", Toast.LENGTH_LONG).show();
                                    int hargaawal=Integer.valueOf(ds.child("harga").getValue().toString())/2;
                                    ClassLelang lelangbaru = new ClassLelang(listClassReq.get(i).getIdbarang(), Integer.valueOf(ds.child("harga").getValue().toString()), 0,hargaawal, "");
                                    dbreftemp = null;
                                    dbreftemp = FirebaseDatabase.getInstance().getReference().child("ClassLelang");
                                    String key = dbreftemp.push().getKey();
                                    dbreftemp.child(key).setValue(lelangbaru);
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });


            }
        });

        FirebaseDatabase.getInstance().getReference().child("ClassRequestLelang").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    if(Integer.parseInt(ds.child("masuklelang").getValue().toString())==1){
                        ClassRequestLelang semua_Class_req = new ClassRequestLelang();
                        semua_Class_req.setIdbarang(ds.child("idbarang").getValue().toString());
                        semua_Class_req.setMasuklelang(Integer.parseInt(ds.child("masuklelang").getValue().toString()));
                        listClassReq.add(semua_Class_req);
                    }
                }
                for(int a = 0; a < listClassReq.size(); a++){
                    test.add(listClassReq.get(a).getIdbarang());
                }
                user = new String[listClassReq.size()];
                user = test.toArray(user);
                AdapterMenuAdmin ama = new AdapterMenuAdmin(MasterLelang.this, user);
                listView.setAdapter(ama);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        TextView tv = view.findViewById(R.id.layoutjudul);
                        a = tv.getText().toString();
                        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("ClassRequestLelang");
                        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (DataSnapshot ds:dataSnapshot.getChildren()){
                                    if(ds.child("idbarang").getValue().toString().equals(a)){
                                        ClassRequestLelang updatelelang = new ClassRequestLelang();
                                        if(ds.child("masuklelang").getValue().toString().equals("1")) {
                                            updatelelang.setMasuklelang(0);
                                            updatelelang.setIdbarang(ds.child("idbarang").getValue().toString());
                                        }
                                        databaseReference.child(ds.getKey()).setValue(updatelelang).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Toast.makeText(MasterLelang.this, "berhasil approve", Toast.LENGTH_SHORT).show();
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
