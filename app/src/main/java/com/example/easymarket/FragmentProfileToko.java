package com.example.easymarket;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentProfileToko extends Fragment {


    public FragmentProfileToko() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_profile_toko, container, false);
    }

    ImageView profil;
    EditText nama;
    TextView email,verif,rating;
    Spinner daerah;
    Button request,simpan;
    ClassToko tokologin;
    Uri selected;
    public ArrayList<ClassBarang> listClassBarang= new ArrayList<>();
    ArrayList<ClassRating> listClassRating = new ArrayList<>();

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && data!=null){
            selected = data.getData();
            profil.setBackgroundResource(0);
            profil.setImageURI(selected);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        profil=view.findViewById(R.id.ivFotoProfilToko);
        nama=view.findViewById(R.id.etNamaToko);
        daerah=view.findViewById(R.id.spDaerahAsalToko);
        request=view.findViewById(R.id.btnRequestVerifikasi);
        simpan=view.findViewById(R.id.btnSimpan);
        verif=view.findViewById(R.id.tvJudulVerifikasi);
        email=view.findViewById(R.id.tvJudulEmailToko);
        rating=view.findViewById(R.id.tvJudulRating);

        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setCornerRadius(100);
        drawable.setColor(Color.WHITE);
        nama.setBackground(drawable);

        GradientDrawable drawable2 = new GradientDrawable();
        drawable2.setShape(GradientDrawable.RECTANGLE);
        drawable2.setCornerRadius(100);
        drawable2.setStroke(8, Color.LTGRAY);
        drawable2.setColor(Color.WHITE);
        daerah.setBackground(drawable2);

        GradientDrawable drawable3 = new GradientDrawable();
        drawable3.setShape(GradientDrawable.RECTANGLE);
        drawable3.setCornerRadius(100);
        drawable3.setColor(Color.BLACK);
        request.setBackground(drawable3);

        GradientDrawable drawable4 = new GradientDrawable();
        drawable4.setShape(GradientDrawable.RECTANGLE);
        drawable4.setCornerRadius(100);
        drawable4.setColor(Color.BLACK);
        simpan.setBackground(drawable4);

        HomeToko homeToko= (HomeToko) getActivity();
        tokologin=homeToko.listClassToko.get(0);

        listClassBarang = (ArrayList<ClassBarang>) getArguments().getSerializable("listClassBarang");

        nama.setText(tokologin.getNama());

        FirebaseDatabase.getInstance().getReference().child("ClassRating").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Boolean cek = true;
                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    if(ds.child("toko").getValue().toString().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())){
                        ClassRating semuaRating =new ClassRating();
                        semuaRating.setRating(Integer.parseInt(ds.child("rating").getValue().toString()));
                        semuaRating.setToko(ds.child("toko").getValue().toString());
                        semuaRating.setYangrating(ds.child("yangrating").getValue().toString());
                        listClassRating.add(semuaRating);
                    }
                }

                int jumlahRating =0;
                for (int i = 0; i < listClassRating.size(); i++) {
                    jumlahRating=jumlahRating+listClassRating.get(i).rating;
                }
                int totalRating = jumlahRating/listClassRating.size();
                rating.setText("Rating : "+totalRating+" / 5");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        int indexemail=tokologin.getEmail().indexOf("@");
        String tampungemail =tokologin.getEmail().substring(0,2)+"********"+tokologin.getEmail().substring(indexemail);
        email.setText("Email : "+tampungemail);

        FirebaseStorage.getInstance().getReference().child("ProfilePicture").child(FirebaseAuth.getInstance().getCurrentUser().getEmail()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(getContext()).load(uri).into(profil);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                profil.setBackgroundResource(R.drawable.ic_account_circle_black_24dp);
            }
        });

        if(tokologin.getAktif().equals("2")){
            verif.setText("Verifikasi : Sudah Terverifikasi");
        }
        else{
            verif.setText("Verifikasi : Tidak Terverifikasi");
        }

        final ArrayList<String>listSpinnerDaerahAsal = new ArrayList<>();
        listSpinnerDaerahAsal.add("Jakarta");
        listSpinnerDaerahAsal.add("Jawa Barat");
        listSpinnerDaerahAsal.add("Jawa Tengah");
        listSpinnerDaerahAsal.add("Yogyakarta");
        listSpinnerDaerahAsal.add("Jawa Timur");
        listSpinnerDaerahAsal.add("Madura");
        listSpinnerDaerahAsal.add("Belum diatur");
        final ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,listSpinnerDaerahAsal);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daerah.setAdapter(dataAdapter2);

        int index=100;
        for (int i = 0; i < listSpinnerDaerahAsal.size(); i++) {
            if(listSpinnerDaerahAsal.get(i).equals(tokologin.getDaerahasal())){
                index=i;
            }
        }

        if(index==100){
            daerah.setSelection(listSpinnerDaerahAsal.size()-1);
        }
        else{
            daerah.setSelection(index);
        }

        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent change = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(change,1);
            }
        });


        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nama.getText().toString().trim().equals("")){
                    Toast.makeText(FragmentProfileToko.this.getContext(), "Nama tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(selected!=null){
                        final ProgressDialog progressDialog = new ProgressDialog(FragmentProfileToko.this.getContext());
                        progressDialog.setTitle("Ganti profile...");
                        progressDialog.show();
                        FirebaseStorage.getInstance().getReference().child("ProfilePicture/"+ FirebaseAuth.getInstance().getCurrentUser().getEmail()).putFile(selected).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                Toast.makeText(FragmentProfileToko.this.getContext(), "Berhasil ganti profile", Toast.LENGTH_SHORT).show();
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
                    final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("ClassToko");
                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Boolean cek = true;
                            for (DataSnapshot ds:dataSnapshot.getChildren()){
                                if(ds.child("email").getValue().toString().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())){
                                    ClassToko updatetoko = new ClassToko();
                                    updatetoko.setDaerahasal(daerah.getSelectedItem().toString());
                                    updatetoko.setRating(Integer.parseInt(ds.child("rating").getValue().toString()));
                                    updatetoko.setAktif(ds.child("aktif").getValue().toString());
                                    updatetoko.setEmail(ds.child("email").getValue().toString());
                                    updatetoko.setNama(nama.getText().toString());
                                    updatetoko.setFirebaseUID(ds.child("firebaseUID").getValue().toString());
                                    updatetoko.setPassword(ds.child("password").getValue().toString());
                                    databaseReference.child(ds.getKey()).setValue(updatetoko).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(FragmentProfileToko.this.getContext(), "berhasil update profile", Toast.LENGTH_SHORT).show();
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

        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tokologin.getAktif().equals("2")){
                    Toast.makeText(FragmentProfileToko.this.getContext(), "Toko anda telah terverifikasi", Toast.LENGTH_SHORT).show();
                }
                else if(tokologin.getAktif().equals("100")){
                    Toast.makeText(FragmentProfileToko.this.getContext(), "Request anda belum dikonfirmasi oleh admin", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(tokologin.getRating()<4 || listClassBarang.size()<5){
                        Toast.makeText(FragmentProfileToko.this.getContext(), "Toko anda harus mempunyai rating minimal 4 dan mempunyai barang minimal 5", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("ClassToko");
                        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                Boolean cek = true;
                                for (DataSnapshot ds:dataSnapshot.getChildren()){
                                    if(ds.child("email").getValue().toString().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())){
                                        ClassToko updatetoko = new ClassToko();
                                        updatetoko.setDaerahasal(ds.child("daerahasal").getValue().toString());
                                        updatetoko.setRating(Integer.parseInt(ds.child("rating").getValue().toString()));
                                        updatetoko.setAktif("100");
                                        updatetoko.setEmail(ds.child("email").getValue().toString());
                                        updatetoko.setNama(ds.child("nama").getValue().toString());
                                        updatetoko.setFirebaseUID(ds.child("firebaseUID").getValue().toString());
                                        updatetoko.setPassword(ds.child("password").getValue().toString());
                                        databaseReference.child(ds.getKey()).setValue(updatetoko).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Toast.makeText(FragmentProfileToko.this.getContext(), "Toko anda sudah direquest untuk diverifikasi, tunggu konfirmasi dari admin", Toast.LENGTH_SHORT).show();
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
            }
        });

    }
}
