package com.example.easymarket;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;


public class FragmentTerkirim extends Fragment {

    public FragmentTerkirim() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_terkirim, container, false);
    }

    RecyclerView rvterkirim;
    AdapterBatal adapterterkirim;
    ArrayList<ClassNota> listClassNota = new ArrayList<>();
    ArrayList<ClassBarang> listClassBarang = new ArrayList<>();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvterkirim = view.findViewById(R.id.rvTerkirim);

        final HistoryActivity historyActivity = (HistoryActivity)getActivity();

        GradientDrawable drawable3 = new GradientDrawable();
        drawable3.setShape(GradientDrawable.RECTANGLE);
        drawable3.setCornerRadius(25);
        drawable3.setStroke(8, Color.LTGRAY);
        drawable3.setColor(Color.WHITE);
        rvterkirim.setBackground(drawable3);

        for (int i = 0; i < historyActivity.listClassNota.size(); i++) {
            if(historyActivity.listClassNota.get(i).namauser.equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())&&historyActivity.listClassNota.get(i).posisi==3){
                listClassNota.add(historyActivity.listClassNota.get(i));
            }
        }
        for (int i = 0; i < historyActivity.listClassBarang.size(); i++) {
            for (int j = 0; j < listClassNota.size(); j++) {
                if(historyActivity.listClassBarang.get(i).idbarang.equals(listClassNota.get(j).idbarang)){
                    listClassBarang.add(historyActivity.listClassBarang.get(i));
                }
            }
        }
        rvterkirim.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterterkirim=new AdapterBatal(listClassNota, listClassBarang, new RVClickListener() {
            @Override
            public void recyclerViewListBarangClick(View v, int posisi) {
                Intent i = new Intent(FragmentTerkirim.this.getContext(),NotaActivity.class);
                i.putExtra("liat",listClassNota);
                i.putExtra("indeks",posisi);
                i.putExtra("posisi",3);
                startActivity(i);
            }
        });
        rvterkirim.setAdapter(adapterterkirim);
    }

}
