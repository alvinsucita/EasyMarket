package com.example.easymarket;


import android.content.Intent;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;

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

        HomeToko homeToko= (HomeToko) getActivity();
        tokologin=homeToko.listClassToko.get(0);

        listClassBarang = (ArrayList<ClassBarang>) getArguments().getSerializable("listClassBarang");

        nama.setText(tokologin.getNama());
        rating.setText("Rating : "+tokologin.getRating()+" / 5");

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

            }
        });

    }
}
