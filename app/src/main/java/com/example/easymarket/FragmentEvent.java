package com.example.easymarket;


import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentEvent extends Fragment {


    public FragmentEvent() {
        // Required empty public constructor
    }


    ImageView ivBarangEvent1,ivEvent1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_fragment_event, container, false);

        ivBarangEvent1 = v.findViewById(R.id.ivBarangEvent1);
        ivEvent1 = v.findViewById(R.id.ivEvent1);
        ivBarangEvent1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //infobarangevent

            }
        });
        ivEvent1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //infoevent
            }
        });



        return v;
    }

}
