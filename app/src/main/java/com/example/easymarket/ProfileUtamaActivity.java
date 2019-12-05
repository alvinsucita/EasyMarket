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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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


    ImageView img,profile;
    TextView tvNama,tvUmur,tvGender,tvDaerahAsal, tvEmail;
    Button simpan;
    ArrayList<User> listUser = new ArrayList<>();
    ArrayList<Barang> listBarang = new ArrayList<>();
    ArrayList<Toko> listToko = new ArrayList<>();
    ArrayList<ClassWishlist> listWishlist = new ArrayList<>();
    EditText nama;
    Uri selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_utama);

        tvNama = findViewById(R.id.etNamProfilUtamaActivity);
        tvUmur = findViewById(R.id.tvShowUmur);
        tvGender = findViewById(R.id.tvShowGender);
        tvDaerahAsal = findViewById(R.id.tvShowDaerah);
        tvEmail = findViewById(R.id.tvShowEmail);
        profile=findViewById(R.id.imgViewProfileUtamaActivity);
        simpan = findViewById(R.id.btSimpanProfile);
        nama = findViewById(R.id.etNamProfilUtamaActivity);

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

        Intent j = getIntent();
        listUser= (ArrayList<User>) j.getSerializableExtra("listUser");
        listToko= (ArrayList<Toko>) j.getSerializableExtra("listToko");
        listBarang= (ArrayList<Barang>) j.getSerializableExtra("listBarang");
        listWishlist= (ArrayList<ClassWishlist>) j.getSerializableExtra("listWishlist");

        for (int i = 0; i < listUser.size(); i++) {
//            if(listUser.get(i).getAktif().equals("1")){
//                int indexalamatemail = listUser.get(i).getEmail().indexOf("@");
//                String hiddenemail =listUser.get(i).getEmail().substring(0,2)+"******"+listUser.get(i).getEmail().substring(indexalamatemail,listUser.get(i).email.length());
//                tvNama.setText(listUser.get(i).getNama());
//                tvUmur.setText(listUser.get(i).getUmur()+" Thn");
//                tvGender.setText(listUser.get(i).getGender());
//                tvDaerahAsal.setText(listUser.get(i).getDaerahasal());
//                tvEmail.setText(hiddenemail);
//            }
        }

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


        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("User");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Boolean cek = true;
                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    if(ds.child("email").getValue().toString().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())){
                        nama.setText(ds.child("nama").getValue().toString());
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
                    final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("User");
                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Boolean cek = true;
                            for (DataSnapshot ds:dataSnapshot.getChildren()){
                                if(ds.child("email").getValue().toString().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())){
                                    User updateuser = new User();
                                    updateuser.setDaerahasal("");
                                    updateuser.setEmail(FirebaseAuth.getInstance().getCurrentUser().getEmail());
                                    updateuser.setGender("");
                                    updateuser.setNama(nama.getText().toString());
                                    updateuser.setPassword(ds.child("password").getValue().toString());
                                    updateuser.setUmur("");
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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_edit_profile,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.item_edit_profile){
            Intent a = new Intent(ProfileUtamaActivity.this, EditProfileActivity.class);
            a.putExtra("listUser", listUser);
            a.putExtra("listBarang", listBarang);
            a.putExtra("listWishlist", listWishlist);
            a.putExtra("listToko", listToko);
            startActivity(a);
        }
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