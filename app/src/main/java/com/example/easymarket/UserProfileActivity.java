package com.example.easymarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class UserProfileActivity extends AppCompatActivity {


    EditText tvGender,tvNama,tvEmail,tvPass,tvAsalDaerah,tvUmur;
    Button btnBack,btnUpdate;
    ImageView img;
    ArrayList<User> listUser = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        tvGender = findViewById(R.id.tvGenderUserProfileActivity);
        tvNama = findViewById(R.id.tvNamaUserProfileActivity);
        tvUmur = findViewById(R.id.tvUmurUserProfileActivity);
        tvEmail = findViewById(R.id.tvEmailUserProfileActivity);
        tvPass = findViewById(R.id.tvPassUserProfileActivity);
        tvAsalDaerah = findViewById(R.id.tvDaerahAsalUserProfileActivity);
        btnBack = findViewById(R.id.btnBackUserProfileActivity);
        btnUpdate = findViewById(R.id.btnUpdateUserProfileActivity);
        img = findViewById(R.id.imgUserProfileActivity);


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update();
            }
        });

        Intent i = getIntent();
        String userlogin;
        String aktif="0";
        if(i.hasExtra("listUser")){
            listUser= (ArrayList<User>) i.getSerializableExtra("listUser");
            for (int j = 0; j < listUser.size(); j++) {
                if(listUser.get(j).aktif.equals("1")){
                    tvNama.setText(listUser.get(j).getNama());
                    tvGender.setText(listUser.get(j).getGender());
                    tvAsalDaerah.setText(listUser.get(j).getDaerahasal());
                    tvUmur.setText(listUser.get(j).getUmur());
                    tvEmail.setText(listUser.get(j).getEmail());
                    tvPass.setText(listUser.get(j).getPassword());
                }
            }
        }
        if(i.hasExtra("adayanglogin")){
            aktif="1";
        }


    }

    public void back(){
        Intent i = new Intent(UserProfileActivity.this,Home.class);
    }
    public void update(){
        for (int j = 0; j < listUser.size(); j++) {
            if(listUser.get(j).aktif.equals("1")){
                listUser.get(j).setNama(tvNama.getText().toString());
                listUser.get(j).setGender(tvGender.getText().toString());
                listUser.get(j).setDaerahasal(tvAsalDaerah.getText().toString());
                listUser.get(j).setUmur(tvUmur.getText().toString());
                listUser.get(j).setEmail(tvEmail.getText().toString());
                listUser.get(j).setPassword(tvPass.getText().toString());
            }
        }
        Toast.makeText(this, "berhasil", Toast.LENGTH_SHORT).show();
    }

}
