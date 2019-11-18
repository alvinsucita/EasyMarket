package com.example.easymarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class EditProfileActivity extends AppCompatActivity {

    ImageView img;
    EditText tvNama,tvPasswordLama,tvPasswordBaru,tvConfirmPassword;
    Button btnUpload,btnUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        tvNama = findViewById(R.id.tvNamaEditProfile);
        tvPasswordLama = findViewById(R.id.tvPasswordLamaEditProfile);
        tvPasswordBaru = findViewById(R.id.tvPasswordBaruEditProfile);
        tvConfirmPassword = findViewById(R.id.tvConfirmPasswordBaruEditProfile);
        btnUpdate = findViewById(R.id.btnUpdateEditProfile);
        btnUpload = findViewById(R.id.btnUploadEditProfile);

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Upload();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Update();
            }
        });

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public void Update(){

    }
    public void Upload(){

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id== android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
