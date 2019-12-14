package com.example.easymarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HalamanCover extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_cover);


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                DatabaseReference databaseReference_toko = FirebaseDatabase.getInstance().getReference().child("ClassToko");
                if(FirebaseAuth.getInstance().getCurrentUser()!=null){
                    databaseReference_toko.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Boolean cek = true;
                            if(FirebaseAuth.getInstance().getCurrentUser()!=null){
                                for (DataSnapshot ds:dataSnapshot.getChildren()){
                                    if(FirebaseAuth.getInstance().getCurrentUser().getEmail().equals(ds.child("email").getValue().toString())){
                                        cek=false;
                                    }
                                }
                                if(cek==false){
                                    ///LoginToko
                                    Intent i = new Intent(HalamanCover.this,HomeToko.class);
                                    startActivity(i);
                                }
                                else {
                                    //LoginUser
                                    Intent i = new Intent(HalamanCover.this,Home.class);
                                    startActivity(i);
                                }
                            }
                            else {
                                FirebaseAuth.getInstance().signInWithEmailAndPassword("guest@guest.com","guest123");
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                else{
                    Intent i = new Intent(HalamanCover.this,Home.class);
                    startActivity(i);
                }
            }
        }, 4000);
    }
}
