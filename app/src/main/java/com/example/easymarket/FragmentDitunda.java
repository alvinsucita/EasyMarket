package com.example.easymarket;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDitunda extends Fragment {


    public FragmentDitunda() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_ditunda, container, false);
    }

    RecyclerView rvditunda;
    AdapterBatal adapterditunda;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final HistoryActivity historyActivity = (HistoryActivity)getActivity();

        rvditunda = view.findViewById(R.id.rvDitunda);

        GradientDrawable drawable3 = new GradientDrawable();
        drawable3.setShape(GradientDrawable.RECTANGLE);
        drawable3.setCornerRadius(25);
        drawable3.setStroke(8, Color.LTGRAY);
        drawable3.setColor(Color.WHITE);
        rvditunda.setBackground(drawable3);

        rvditunda.setLayoutManager(new LinearLayoutManager(getContext()));
        rvditunda.setAdapter(adapterditunda);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}
