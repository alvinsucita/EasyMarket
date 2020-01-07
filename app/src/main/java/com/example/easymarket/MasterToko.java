package com.example.easymarket;

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

public class MasterToko extends AppCompatActivity {

    //2 == power badge?

    ListView listView;
    ArrayList<ClassToko> listClassToko = new ArrayList<>();

    ArrayList<String> test = new ArrayList<>();
    String[] user;
    String a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_toko);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listView = findViewById(R.id.lvtoko);

        FirebaseDatabase.getInstance().getReference().child("ClassToko").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    ClassToko semua_Class_toko = new ClassToko();
                    semua_Class_toko.setEmail(ds.child("email").getValue().toString());
                    semua_Class_toko.setNama(ds.child("nama").getValue().toString());
                    semua_Class_toko.setPassword(ds.child("password").getValue().toString());
                    semua_Class_toko.setDaerahasal(ds.child("daerahasal").getValue().toString());
                    semua_Class_toko.setRating(Integer.valueOf(ds.child("rating").getValue().toString()));
                    semua_Class_toko.setFirebaseUID(ds.child("firebaseUID").getValue().toString());
                    semua_Class_toko.setAktif(ds.child("aktif").getValue().toString());
                    listClassToko.add(semua_Class_toko);
                }
                for(int a = 0; a< listClassToko.size(); a++){
                    test.add(listClassToko.get(a).getNama());
                }
                user = new String[listClassToko.size()];
                user = test.toArray(user);
                AdapterMenuAdmin ama = new AdapterMenuAdmin(MasterToko.this, user);
                listView.setAdapter(ama);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        TextView tv = view.findViewById(R.id.layoutjudul);
                        a = tv.getText().toString();
                        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("ClassToko");
                        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (DataSnapshot ds:dataSnapshot.getChildren()){
                                    if(ds.child("nama").getValue().toString().equals(a)){
                                        ClassToko updatetoko = new ClassToko();
                                        if(ds.child("aktif").getValue().toString().equals("0")) updatetoko.setAktif("1");
                                        else if(!ds.child("aktif").getValue().toString().equals("0")) updatetoko.setAktif("0");
                                        updatetoko.setEmail(ds.child("email").getValue().toString());
                                        updatetoko.setNama(ds.child("nama").getValue().toString());
                                        updatetoko.setDaerahasal(ds.child("daerahasal").getValue().toString());
                                        updatetoko.setRating(Integer.valueOf(ds.child("rating").getValue().toString()));
                                        updatetoko.setPassword(ds.child("password").getValue().toString());
                                        updatetoko.setFirebaseUID(ds.child("firebaseUID").getValue().toString());
                                        databaseReference.child(ds.getKey()).setValue(updatetoko).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Toast.makeText(MasterToko.this, "berhasil update", Toast.LENGTH_SHORT).show();
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
