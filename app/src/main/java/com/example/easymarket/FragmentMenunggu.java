package com.example.easymarket;


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


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMenunggu extends Fragment {


    public FragmentMenunggu() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_menunggu, container, false);
    }

    RecyclerView rvmenunggu;
    AdapterBatal adaptermenunggu;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final HistoryActivity historyActivity = (HistoryActivity)getActivity();

        rvmenunggu = view.findViewById(R.id.rvMenunggu);

        GradientDrawable drawable3 = new GradientDrawable();
        drawable3.setShape(GradientDrawable.RECTANGLE);
        drawable3.setCornerRadius(25);
        drawable3.setStroke(8, Color.LTGRAY);
        drawable3.setColor(Color.WHITE);
        rvmenunggu.setBackground(drawable3);

        rvmenunggu.setLayoutManager(new LinearLayoutManager(getContext()));
        rvmenunggu.setAdapter(adaptermenunggu);
    }

}
