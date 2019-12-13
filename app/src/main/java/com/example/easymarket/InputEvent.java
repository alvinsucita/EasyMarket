package com.example.easymarket;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InputEvent extends AppCompatActivity {

    EditText judul, desc;
    Button add;
    DatabaseReference dbRefE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_event);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        judul = findViewById(R.id.etaddeventname);
        desc = findViewById(R.id.etaddeventdesc);
        add = findViewById(R.id.buttoninsert);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(judul.getText().toString().equals("") || desc.getText().toString().equals(""));
                ClassEvent eventbaru = new ClassEvent(judul.getText().toString(), desc.getText().toString());
                dbRefE = FirebaseDatabase.getInstance().getReference().child("ClassEvent");
                String key = dbRefE.push().getKey();
                dbRefE.child(key).setValue(eventbaru).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(InputEvent.this, "Event baru berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                    }
                });
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
