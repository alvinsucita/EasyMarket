package com.example.easymarket;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.firebase.ui.database.FirebaseRecyclerAdapter;

public class ManageUsers extends AppCompatActivity {

//    ListView listView;
//    ArrayList<ClassUser> listClassUser = new ArrayList<>();
//    ArrayList<ClassBarang> listClassBarang = new ArrayList<>();
//    ArrayList<ClassToko> listClassToko = new ArrayList<>();
//    ArrayList<ClassWishlist> listWishlist = new ArrayList<>();
//
//    ArrayList<String> test = new ArrayList<>();
//    String[] user;
    private Button ban;
    private LinearLayoutManager llm;
    private FirebaseRecyclerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_user);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        Intent i = getIntent();
//        if (i.hasExtra("listClassUser")) {
//            listClassUser = (ArrayList<ClassUser>) i.getSerializableExtra("listClassUser");
//            listClassToko = (ArrayList<ClassToko>) i.getSerializableExtra("listClassToko");
//            listClassBarang = (ArrayList<ClassBarang>) i.getSerializableExtra("listClassBarang");
//            listWishlist = (ArrayList<ClassWishlist>) i.getSerializableExtra("listWishlist");
//            for(int a = 0; a< listClassUser.size(); a++){
//                test.add(listClassUser.get(a).getNama());
//            }
//            user = new String[listClassUser.size()];
//            user = test.toArray(user);
//        }
//
//        listView = findViewById(R.id.lvusers);
//        AdapterMenuAdmin ama = new AdapterMenuAdmin(this, user, user);
//        listView.setAdapter(ama);
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
