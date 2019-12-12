package com.example.easymarket;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

public class ProfileUtamaActivity extends AppCompatActivity {


    ImageView profile;
    TextView tvNama,tvEmail;
    Button simpan;
    EditText nama;
    Uri selected;
    Spinner spUmur,spDaerah,spGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_utama);

        tvNama = findViewById(R.id.etNamProfilUtamaActivity);
        tvEmail = findViewById(R.id.tvShowEmail);
        profile=findViewById(R.id.imgViewProfileUtamaActivity);
        simpan = findViewById(R.id.btSimpanProfile);
        nama = findViewById(R.id.etNamProfilUtamaActivity);
        spUmur=findViewById(R.id.spUmur);
        spDaerah=findViewById(R.id.spDaerahAsal);
        spGender=findViewById(R.id.spJenisKelamin);

        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setCornerRadius(100);
        drawable.setColor(Color.WHITE);
        tvNama.setBackground(drawable);

        GradientDrawable drawable2 = new GradientDrawable();
        drawable2.setShape(GradientDrawable.RECTANGLE);
        drawable2.setCornerRadius(100);
        drawable2.setColor(Color.BLACK);
        simpan.setBackground(drawable2);

        final ArrayList<String>listSpinnerUmur = new ArrayList<>();
        final ArrayList<String>listSpinnerDaerahAsal = new ArrayList<>();
        final ArrayList<String>listSpinnerGender = new ArrayList<>();

        listSpinnerUmur.add("18");
        listSpinnerUmur.add("19");
        listSpinnerUmur.add("20");
        listSpinnerUmur.add("21");
        listSpinnerUmur.add("22");
        listSpinnerUmur.add("23");
        listSpinnerUmur.add("24");
        listSpinnerUmur.add("25");
        listSpinnerUmur.add("26");
        listSpinnerUmur.add("27");
        listSpinnerUmur.add("28");
        listSpinnerUmur.add("29");
        listSpinnerUmur.add("30");
        listSpinnerUmur.add("31");
        listSpinnerUmur.add("32");
        listSpinnerUmur.add("33");
        listSpinnerUmur.add("34");
        listSpinnerUmur.add("35");
        listSpinnerUmur.add("36");
        listSpinnerUmur.add("37");
        listSpinnerUmur.add("38");
        listSpinnerUmur.add("39");
        listSpinnerUmur.add("40");
        listSpinnerUmur.add("41");
        listSpinnerUmur.add("42");
        listSpinnerUmur.add("43");
        listSpinnerUmur.add("44");
        listSpinnerUmur.add("45");
        listSpinnerUmur.add("46");
        listSpinnerUmur.add("47");
        listSpinnerUmur.add("48");
        listSpinnerUmur.add("49");
        listSpinnerUmur.add("50");
        listSpinnerUmur.add("51");
        listSpinnerUmur.add("52");
        listSpinnerUmur.add("53");
        listSpinnerUmur.add("54");
        listSpinnerUmur.add("55");
        listSpinnerUmur.add("56");
        listSpinnerUmur.add("57");
        listSpinnerUmur.add("58");
        listSpinnerUmur.add("59");
        listSpinnerUmur.add("60");
        listSpinnerUmur.add("Belum diatur");
        listSpinnerDaerahAsal.add("Jakarta");
        listSpinnerDaerahAsal.add("Jawa Barat");
        listSpinnerDaerahAsal.add("Jawa Tengah");
        listSpinnerDaerahAsal.add("Yogyakarta");
        listSpinnerDaerahAsal.add("Jawa Timur");
        listSpinnerDaerahAsal.add("Madura");
        listSpinnerDaerahAsal.add("Belum diatur");
        listSpinnerGender.add("Pria");
        listSpinnerGender.add("Wanita");
        listSpinnerGender.add("Belum diatur");

        final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,listSpinnerUmur);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spUmur.setAdapter(dataAdapter);

        final ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,listSpinnerDaerahAsal);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDaerah.setAdapter(dataAdapter2);

        final ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,listSpinnerGender);
        dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spGender.setAdapter(dataAdapter3);

        //ambil gambar untuk profil
        FirebaseStorage.getInstance().getReference().child("ProfilePicture").child(FirebaseAuth.getInstance().getCurrentUser().getEmail()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(getApplicationContext()).load(uri).into(profile);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                profile.setBackgroundResource(R.drawable.baseline_person_outline_black_18dp);
            }
        });


        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("ClassUser");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Boolean cek = true;
                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    if(ds.child("email").getValue().toString().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())){
                        int index=100,index2=100,index3=100;
                        int indexemail=ds.child("email").getValue().toString().indexOf("@");
                        String tampungemail =ds.child("email").getValue().toString().substring(0,2)+"********"+ds.child("email").getValue().toString().substring(indexemail);
                        nama.setText(ds.child("nama").getValue().toString());
                        tvEmail.setText(tampungemail);
                        String strumur=ds.child("umur").getValue().toString();
                        String strdaerah=ds.child("daerahasal").getValue().toString();
                        String strgender=ds.child("gender").getValue().toString();

                        for (int i = 0; i < listSpinnerUmur.size(); i++) {
                            if(listSpinnerUmur.get(i).equals(strumur)){
                                index=i;
                            }
                        }
                        if(index<100){
                            spUmur.setSelection(index);
                        }
                        else{
                            spUmur.setSelection(listSpinnerUmur.size()-1);
                        }

                        for (int i = 0; i < listSpinnerDaerahAsal.size(); i++) {
                            if(listSpinnerDaerahAsal.get(i).equals(strdaerah)){
                                index2=i;
                            }
                        }
                        if(index2<100){
                            spDaerah.setSelection(index2);
                        }
                        else{
                            spDaerah.setSelection(listSpinnerDaerahAsal.size()-1);
                        }

                        for (int i = 0; i < listSpinnerGender.size(); i++) {
                            if(listSpinnerGender.get(i).equals(strgender)){
                                index=i;
                            }
                        }
                        if(index<100){
                            spGender.setSelection(index);
                        }
                        else{
                            spGender.setSelection(listSpinnerGender.size()-1);
                        }

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //simpan perubahan
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nama.getText().toString().trim().equals("")){
                    Toast.makeText(ProfileUtamaActivity.this, "Nama tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(selected!=null){
                        final ProgressDialog progressDialog = new ProgressDialog(ProfileUtamaActivity.this);
                        progressDialog.setTitle("Ganti profile...");
                        progressDialog.show();
                        FirebaseStorage.getInstance().getReference().child("ProfilePicture/"+ FirebaseAuth.getInstance().getCurrentUser().getEmail()).putFile(selected).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                Toast.makeText(ProfileUtamaActivity.this, "Berhasil ganti profile", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                                double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                                progressDialog.setMessage("Upload profile "+(int)progress+"%");
                                if (progress==100){
                                    progressDialog.dismiss();
                                }
                            }
                        });
                    }
                    final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("ClassUser");
                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Boolean cek = true;
                            for (DataSnapshot ds:dataSnapshot.getChildren()){
                                if(ds.child("email").getValue().toString().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())){
                                    ClassUser updateuser = new ClassUser();
                                    updateuser.setDaerahasal(spDaerah.getSelectedItem().toString());
                                    //updateuser.setAktif("1");
                                    updateuser.setEmail(FirebaseAuth.getInstance().getCurrentUser().getEmail());
                                    updateuser.setGender(spGender.getSelectedItem().toString());
                                    updateuser.setNama(nama.getText().toString());
                                    updateuser.setPassword(ds.child("password").getValue().toString());
                                    updateuser.setUmur(spUmur.getSelectedItem().toString());
                                    databaseReference.child(ds.getKey()).setValue(updateuser).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(ProfileUtamaActivity.this, "berhasil update profile", Toast.LENGTH_SHORT).show();
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
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gantiFoto();
            }
        });

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id== android.R.id.home){
            Intent a = new Intent(ProfileUtamaActivity.this, Home.class);
            startActivity(a);
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==1 && data!=null){
            selected = data.getData();
            profile.setBackgroundResource(0);
            profile.setImageURI(selected);
        }
    }

    public void gantiFoto() {
        Intent change = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(change,1);
    }
}