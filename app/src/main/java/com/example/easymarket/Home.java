package com.example.easymarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Home extends AppCompatActivity {

    BottomNavigationView bottomNavHome;
    ArrayList<ClassBarang> listClassBarang = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        changeFragment(new FragmentHome());
        bottomNavHome = findViewById(R.id.bottomNavHome);
        bottomNavHome.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(menuItem.getItemId( )== R.id.itemHome){
                    changeFragment(new FragmentHome());
                }
                else if(menuItem.getItemId( )== R.id.itemLelang){
                    changeFragment(new FragmentLelang());
                }
                else if(menuItem.getItemId( )== R.id.itemEvent){
                    changeFragment(new FragmentEvent());
                }
                Date a = new Date();
                SimpleDateFormat time = new SimpleDateFormat("hh:mm aa");
                Calendar now = Calendar.getInstance();
                Toast.makeText(Home.this,now.get(Calendar.DAY_OF_MONTH)+"/"+now.get(Calendar.MONTH)+"/"+now.get(Calendar.YEAR)+" - "+time.format(a), Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu,menu);

        if(FirebaseAuth.getInstance().getCurrentUser()!=null){
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("ClassUser");
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Boolean cek = true;
                    for (DataSnapshot ds:dataSnapshot.getChildren()){
                        if(ds.child("email").getValue().toString().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())){
                            String  nama = ds.child("nama").getValue().toString();
                            menu.getItem(2).setTitle("Hai, "+nama);
                            cek=false;
                        }
                    }
                    if(cek){
                        menu.getItem(0).setVisible(true);
                        menu.getItem(1).setVisible(false);
                        menu.getItem(2).setVisible(false);
                        menu.getItem(3).setVisible(false);
                        menu.getItem(4).setVisible(false);
                    }
                    else {
                        menu.getItem(0).setVisible(false);
                        menu.getItem(1).setVisible(true);
                        menu.getItem(2).setVisible(true);
                        menu.getItem(3).setVisible(true);
                        menu.getItem(4).setVisible(true);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.itemLogin){
            Intent i = new Intent(Home.this,Login.class);
            startActivity(i);
        }
        else if(item.getItemId()==R.id.itemLogout){
            FirebaseAuth.getInstance().signOut();
            FirebaseAuth.getInstance().signInWithEmailAndPassword("guest@guest.com","guest123");
            Intent i = new Intent(Home.this,Home.class);
            startActivity(i);
        }
        else if(item.getItemId() == R.id.itemProfile){
            Intent i = new Intent(Home.this,ProfileUtamaActivity.class);
            startActivity(i);
        }
        else if(item.getItemId()==R.id.itemWishlist){
            Intent i = new Intent(Home.this,WishList.class);
            startActivity(i);
        }
        else if(item.getItemId()==R.id.itemHistory){
            Intent i = new Intent(Home.this,HistoryActivity.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    public void changeFragment(Fragment f){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Bundle bundle = new Bundle();
        f.setArguments(bundle);
        ft.replace(R.id.containerHome, f);
        ft.commit();
    }
}