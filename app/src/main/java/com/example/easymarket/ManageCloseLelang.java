package com.example.easymarket;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
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
    DatabaseReference dbreftemp;

    ArrayList<String> test = new ArrayList<>();
    String[] user;
    String a;

    //requestlelang 100

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_close_lelang);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listView = findViewById(R.id.lvlelang);
        FirebaseDatabase.getInstance().getReference().child("ClassRequestLelang").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    if(Integer.parseInt(ds.child("masuklelang").getValue().toString())==1){
                        ClassLelang semua_Class_lelang = new ClassLelang();
                        semua_Class_lelang.setIdbarang(ds.child("idbarang").getValue().toString());
                        listClassLelang.add(semua_Class_lelang);
                    }
                }
                for(int a = 0; a < listClassLelang.size(); a++){
                    test.add(listClassLelang.get(a).getIdbarang());
                }
                user = new String[listClassLelang.size()];
                user = test.toArray(user);
                AdapterMenuAdmin ama = new AdapterMenuAdmin(ManageCloseLelang.this, user);
                listView.setAdapter(ama);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                        TextView tv = view.findViewById(R.id.layoutjudul);
//                        a = tv.getText().toString();
//                        Toast.makeText(MasterLelang.this, a, Toast.LENGTH_LONG).show();
                        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("ClassRequestLelang");
                        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (DataSnapshot ds:dataSnapshot.getChildren()){
                                    if(ds.child("idbarang").getValue().toString().equals(a)){
                                        ClassRequestLelang updatelelang = new ClassRequestLelang();
//                                        Toast.makeText(MasterLelang.this, ds.child("masuklelang").getValue().toString(), Toast.LENGTH_LONG).show();
                                        if(ds.child("masuklelang").getValue().toString().equals("1")) {
                                            updatelelang.setMasuklelang(0);
                                            updatelelang.setIdbarang(ds.child("idbarang").getValue().toString());
                                        }
                                        databaseReference.child(ds.getKey()).setValue(updatelelang).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Toast.makeText(ManageCloseLelang.this, "berhasil approve", Toast.LENGTH_SHORT).show();
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
