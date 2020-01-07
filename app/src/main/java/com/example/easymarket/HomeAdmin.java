package com.example.easymarket;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeAdmin extends AppCompatActivity {

    TextView user, toko, req, lelang, refund, nota;
    Button refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);
        user = findViewById(R.id.tvtotaluser);
        toko = findViewById(R.id.tvtotaltoko);
        req = findViewById(R.id.tvtotalreqlelang);
        lelang = findViewById(R.id.tvtotallelang);
        refund = findViewById(R.id.tvtotalrefund);
        nota = findViewById(R.id.tvtotalnota);
        refresh = findViewById(R.id.btnrefreshadmin);

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(getIntent());
            }
        });

        FirebaseDatabase.getInstance().getReference().child("ClassUser").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user.setText("Total User: "+ (int)dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        FirebaseDatabase.getInstance().getReference().child("ClassToko").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                toko.setText("Total Toko: "+ (int)dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        FirebaseDatabase.getInstance().getReference().child("ClassRequestLelang").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int ctr = 0;
                for (DataSnapshot ds:dataSnapshot.getChildren()) {
                    if (Integer.parseInt(ds.child("masuklelang").getValue().toString()) == 1) {
                        ctr++;
                    }
                }
                refund.setText("Request Lelang Pending: "+ ctr);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        FirebaseDatabase.getInstance().getReference().child("ClassLelang").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                lelang.setText("Lelang Yang Berjalan: "+ (int)dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        FirebaseDatabase.getInstance().getReference().child("ClassNota").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int ctr = 0;
                for (DataSnapshot ds:dataSnapshot.getChildren()) {
                    if (Integer.parseInt(ds.child("posisi").getValue().toString()) == 9) {
                        ctr++;
                    }
                }
                refund.setText("Refund Pending: "+ ctr);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        FirebaseDatabase.getInstance().getReference().child("ClassNota").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                nota.setText("Total Transaksi: "+ (int)dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menuadmin,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.addevent){
            Intent i = new Intent(HomeAdmin.this,InputEvent.class);
            startActivity(i);
        }
        else if(item.getItemId()==R.id.manageuser){
            Intent i = new Intent(HomeAdmin.this,ManageUsers.class);
            startActivity(i);
        }
        else if(item.getItemId()==R.id.managetoko){
            Intent i = new Intent(HomeAdmin.this,MasterToko.class);
            startActivity(i);
        }
        else if(item.getItemId()==R.id.veriftoko){
            Intent i = new Intent(HomeAdmin.this,ManageVerifToko.class);
            startActivity(i);
        }
        else if(item.getItemId()==R.id.closelelang){
            Intent i = new Intent(HomeAdmin.this,ManageCloseLelang.class);
            startActivity(i);
        }
        else if(item.getItemId()==R.id.daftarnota){
            Intent i = new Intent(HomeAdmin.this,DaftarNota.class);
            startActivity(i);
        }
        else if(item.getItemId()==R.id.managerefund){
            Intent i = new Intent(HomeAdmin.this,ManageRefund.class);
            startActivity(i);
        }
        else if(item.getItemId()==R.id.managelelang){
            Intent i = new Intent(HomeAdmin.this,MasterLelang.class);
            startActivity(i);
        }
        else if(item.getItemId()==R.id.logoutadmin){
            Intent i = new Intent(HomeAdmin.this,Login.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
}
