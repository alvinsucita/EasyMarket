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

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeRootActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_root);


        changeFragment(new FragmentHome());
        bottomNavigationView=findViewById(R.id.btmNavRootHome);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(menuItem.getItemId( )== R.id.itemHome){
                    changeFragment(new FragmentHome());
                }
                else if(menuItem.getItemId( )== R.id.itemEvent){
                    changeFragment(new FragmentEvent());
                }
                else if(menuItem.getItemId( )== R.id.itemLelang){
                    changeFragment(new FragmentLelang());
                }
                else if(menuItem.getItemId( )== R.id.itemHistory){
                    changeFragment(new FragmentHistory());
                }
                return true;
            }
        });

    }

    public void changeFragment(Fragment f){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragmentContainerRootHome, f);
        ft.commit();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.option_menu,menu);
//        if(aktif.equals("0")){
//            menu.getItem(0).setVisible(true);
//            menu.getItem(1).setVisible(false);
//            menu.getItem(2).setVisible(false);
//            menu.getItem(3).setVisible(false);
//            menu.getItem(4).setVisible(false);
//            menu.getItem(5).setVisible(false);
//            menu.getItem(6).setVisible(false);
//            menu.getItem(7).setVisible(false);
//            menu.getItem(8).setVisible(false);
//        }
//        else{
//            menu.getItem(3).setTitle("Hai, "+userlogin+" !");
//            menu.getItem(3).setVisible(true);
//            menu.getItem(0).setVisible(false);
//            menu.getItem(1).setVisible(true);
//            menu.getItem(2).setVisible(true);
//            menu.getItem(4).setVisible(true);
//            menu.getItem(5).setVisible(true);
//            menu.getItem(6).setVisible(true);
//            menu.getItem(7).setVisible(false);
//            menu.getItem(8).setVisible(true);
//        }
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if(item.getItemId()==R.id.itemLogin){
//            Intent i = new Intent(Home.this,Login.class);
//            i = putextra(i);
//            startActivity(i);
//        }
//        else if(item.getItemId()==R.id.itemLogout){
//            Intent i = new Intent(Home.this,Home.class);
//            for (int j = 0; j < listUser.size(); j++) {
//                if(listUser.get(j).aktif.equals("1")){
//                    listUser.get(j).aktif="0";
//                    i = putextra(i);
//                    startActivity(i);
//                }
//            }
//        }
//        else if(item.getItemId()==R.id.itemEvent){
//            Intent i = new Intent(HomeRootActivity.this,EventPage.class);
//            i = putextra(i);
//            startActivity(i);
//        }
//        else if(item.getItemId() == R.id.itemNama){
//            Intent i = new Intent(HomeRootActivity.this,ProfileUtamaActivity.class);
//            i = putextra(i);
//            startActivity(i);
//        }
//        else if(item.getItemId()==R.id.itemWishlist){
//            Intent i = new Intent(HomeRootActivity.this,WishList.class);
//            i = putextra(i);
//            i.putExtra("adayanglogin",aktif);
//            startActivity(i);
//        }
//        else if(item.getItemId()==R.id.itemLelang){
//            Intent i = new Intent(HomeRootActivity.this,LelangActivity.class);
//            i = putextra(i);
//            startActivity(i);
//        }
//        else if(item.getItemId()==R.id.itemCekPengiriman){
//            Intent i = new Intent(HomeRootActivity.this,CekPerjalananActivity.class);
//            startActivity(i);
//        }
//        else if(item.getItemId()==R.id.itemRefund){
//            Intent i = new Intent(HomeRootActivity.this,RefundActivity.class);
//            startActivity(i);
//        }
//        return super.onOptionsItemSelected(item);
//    }


}
