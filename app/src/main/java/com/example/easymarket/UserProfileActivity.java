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


    EditText tvGender,tvNama,tvEmail,tvPass,tvAsalDaerah,tvUmur,tvPassLama;
    Button btnBack,btnUpdate;
    ImageView img;
    ArrayList<ClassUser> listClassUser = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        tvGender = findViewById(R.id.tvGenderUserProfileActivity);
        tvGender.setEnabled(false);
        tvNama = findViewById(R.id.tvNamaUserProfileActivity);
        tvUmur = findViewById(R.id.tvUmurUserProfileActivity);
        tvUmur.setEnabled(false);
        tvEmail = findViewById(R.id.tvEmailUserProfileActivity);
        tvEmail.setEnabled(false);
        tvPass = findViewById(R.id.tvPassUserProfileActivity);
        tvPassLama = findViewById(R.id.tvPassUserProfileActivity);
        tvAsalDaerah = findViewById(R.id.tvDaerahAsalUserProfileActivity);
        tvAsalDaerah.setEnabled(false);
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
        if(i.hasExtra("listClassUser")){
            listClassUser = (ArrayList<ClassUser>) i.getSerializableExtra("listClassUser");
            for (int j = 0; j < listClassUser.size(); j++) {
//                if(listClassUser.get(j).aktif.equals("1")){
//                    tvNama.setText(listClassUser.get(j).getNama());
//                    tvGender.setText(listClassUser.get(j).getGender());
//                    tvAsalDaerah.setText(listClassUser.get(j).getDaerahasal());
//                    tvUmur.setText(listClassUser.get(j).getUmur());
//                    tvEmail.setText(listClassUser.get(j).getEmail());
//                    tvPass.setText(listClassUser.get(j).getPassword());
//                }
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
        for (int j = 0; j < listClassUser.size(); j++) {
//            if(listClassUser.get(j).aktif.equals("1")){
//                if(listClassUser.get(j).getPassword().equals(tvPassLama.getText().toString())){
//                    listClassUser.get(j).setNama(tvNama.getText().toString());
//                    listClassUser.get(j).setPassword(tvPass.getText().toString());
//                }
//            }
        }
        Toast.makeText(this, "berhasilUpdate", Toast.LENGTH_SHORT).show();
    }

}
