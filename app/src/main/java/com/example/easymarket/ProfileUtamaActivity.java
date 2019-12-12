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
    TextView tvEmail;
    Button simpan;
    EditText nama, umur;
    Uri selected;
    Spinner spDaerah,spGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_utama);

        tvEmail = findViewById(R.id.tvShowEmail);
        profile=findViewById(R.id.imgViewProfileUtamaActivity);
        simpan = findViewById(R.id.btSimpanProfile);
        nama = findViewById(R.id.etNamaProfilUtamaActivity);
        umur=findViewById(R.id.etUmurProfilUtamaActivity);
        spDaerah=findViewById(R.id.spDaerahAsal);
        spGender=findViewById(R.id.spJenisKelamin);

        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setCornerRadius(100);
        drawable.setColor(Color.WHITE);
        nama.setBackground(drawable);

        GradientDrawable drawable2 = new GradientDrawable();
        drawable2.setShape(GradientDrawable.RECTANGLE);
        drawable2.setCornerRadius(100);
        drawable2.setColor(Color.WHITE);
        umur.setBackground(drawable2);

        GradientDrawable drawable3 = new GradientDrawable();
        drawable3.setShape(GradientDrawable.RECTANGLE);
        drawable3.setCornerRadius(100);
        drawable3.setStroke(8, Color.LTGRAY);
        drawable3.setColor(Color.WHITE);
        spDaerah.setBackground(drawable3);

        GradientDrawable drawable4 = new GradientDrawable();
        drawable4.setShape(GradientDrawable.RECTANGLE);
        drawable4.setCornerRadius(100);
        drawable4.setStroke(8, Color.LTGRAY);
        drawable4.setColor(Color.WHITE);
        spGender.setBackground(drawable4);

        GradientDrawable drawable5 = new GradientDrawable();
        drawable5.setShape(GradientDrawable.RECTANGLE);
        drawable5.setCornerRadius(100);
        drawable5.setColor(Color.BLACK);
        simpan.setBackground(drawable5);

        final ArrayList<String>listSpinnerUmur = new ArrayList<>();
        final ArrayList<String>listSpinnerDaerahAsal = new ArrayList<>();
        final ArrayList<String>listSpinnerGender = new ArrayList<>();

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
                        umur.setText(ds.child("umur").getValue().toString());
                        tvEmail.setText(tampungemail);
                        String strumur=ds.child("umur").getValue().toString();
                        String strdaerah=ds.child("daerahasal").getValue().toString();
                        String strgender=ds.child("gender").getValue().toString();


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
                                    updateuser.setEmail(FirebaseAuth.getInstance().getCurrentUser().getEmail());
                                    updateuser.setGender(spGender.getSelectedItem().toString());
                                    updateuser.setNama(nama.getText().toString());
                                    updateuser.setPassword(ds.child("password").getValue().toString());
                                    updateuser.setUmur(umur.getText().toString());
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