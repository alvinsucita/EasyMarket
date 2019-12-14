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

public class ManageUsers extends AppCompatActivity {

    ListView listView;
    ArrayList<ClassUser> listClassUser = new ArrayList<>();

    ArrayList<String> test = new ArrayList<>();
    String[] user;
    String a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_user);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listView = findViewById(R.id.lvusers);

        FirebaseDatabase.getInstance().getReference().child("ClassUser").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    ClassUser semua_Class_user = new ClassUser();
                    semua_Class_user.setEmail(ds.child("email").getValue().toString());
                    semua_Class_user.setNama(ds.child("nama").getValue().toString());
                    semua_Class_user.setPassword(ds.child("password").getValue().toString());
                    semua_Class_user.setGender(ds.child("gender").getValue().toString());
                    semua_Class_user.setDaerahasal(ds.child("daerahasal").getValue().toString());
                    semua_Class_user.setUmur(ds.child("umur").getValue().toString());
                    semua_Class_user.setAktif(ds.child("aktif").getValue().toString());
                    listClassUser.add(semua_Class_user);
                }
                for(int a = 0; a< listClassUser.size(); a++){
                    test.add(listClassUser.get(a).getNama());
                }
                user = new String[listClassUser.size()];
                user = test.toArray(user);
                AdapterMenuAdmin ama = new AdapterMenuAdmin(ManageUsers.this, user);
                listView.setAdapter(ama);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        Toast.makeText(ManageUsers.this, position + "", Toast.LENGTH_LONG).show();
                        TextView tv = view.findViewById(R.id.layoutjudul);
                        a = tv.getText().toString();
                        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("ClassUser");
                        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (DataSnapshot ds:dataSnapshot.getChildren()){
                                    if(ds.child("email").getValue().toString().equals(a)){
                                        ClassUser updateuser = new ClassUser();
                                        updateuser.setDaerahasal(ds.child("daerahasal").getValue().toString());
                                        if(ds.child("aktif").getValue().toString().equals("0")) updateuser.setAktif("1");
                                        else if(ds.child("aktif").getValue().toString().equals("1")) updateuser.setAktif("0");
                                        updateuser.setEmail(ds.child("email").getValue().toString());
                                        updateuser.setGender(ds.child("gender").getValue().toString());
                                        updateuser.setNama(ds.child("nama").getValue().toString());
                                        updateuser.setPassword(ds.child("password").getValue().toString());
                                        updateuser.setUmur(ds.child("umur").getValue().toString());
                                        databaseReference.child(ds.getKey()).setValue(updateuser).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Toast.makeText(ManageUsers.this, "berhasil update", Toast.LENGTH_SHORT).show();
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
