package com.example.easymarket;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
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
/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentBatal extends Fragment {


    public FragmentBatal() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_batal, container, false);
    }

    RecyclerView rvbatal;
    AdapterBatal adapterbatal;
    ArrayList<ClassNota> listClassNota = new ArrayList<>();
    ArrayList<ClassBarang> listClassBarang = new ArrayList<>();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvbatal = view.findViewById(R.id.rvBatal);

        final HistoryActivity historyActivity = (HistoryActivity)getActivity();

        GradientDrawable drawable3 = new GradientDrawable();
        drawable3.setShape(GradientDrawable.RECTANGLE);
        drawable3.setCornerRadius(25);
        drawable3.setStroke(8, Color.LTGRAY);
        drawable3.setColor(Color.WHITE);
        rvbatal.setBackground(drawable3);

        for (int i = 0; i < historyActivity.listClassNota.size(); i++) {
            if(historyActivity.listClassNota.get(i).namauser.equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())&&historyActivity.listClassNota.get(i).posisi==4){
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
        rvbatal.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterbatal=new AdapterBatal(listClassNota, listClassBarang, new RVClickListener() {
            @Override
            public void recyclerViewListBarangClick(View v, int posisi) {
                Intent i = new Intent(FragmentBatal.this.getContext(),NotaActivity.class);
                i.putExtra("liat",listClassNota);
                i.putExtra("indeks",posisi);
                i.putExtra("posisi",4);
                startActivity(i);
            }
        });
        rvbatal.setAdapter(adapterbatal);
    }
}
