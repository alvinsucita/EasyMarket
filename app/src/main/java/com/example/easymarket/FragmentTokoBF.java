package com.example.easymarket;


import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTokoBF extends Fragment {

    _AppDatabase db;

    EditText et_nama, et_harga, et_durasi;
    Button btn_submit;
    public FragmentTokoBF() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_toko_b, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db= Room.databaseBuilder(getContext(),_AppDatabase.class,"BarangDB").build();

        et_nama = view.findViewById(R.id.tokoBF_editText_nama);
        et_harga = view.findViewById(R.id.tokoBF_editText_hargaRetail);
        et_durasi = view.findViewById(R.id.tokoBF_editText_durasi);
        btn_submit = view.findViewById(R.id.tokoBF_button_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _BarangBF b = new _BarangBF(
                        et_nama.getText().toString(),
                        et_harga.getText().toString(),
                        et_durasi.getText().toString(),
                        "owner"
                );
                new InsertBarang().execute(b);
            }
        });
    }

    private class InsertBarang extends AsyncTask<_BarangBF,Void,Void> {

        @Override
        protected Void doInBackground(_BarangBF... barangs) {
            db.barangDAO().insertbarang(barangs[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(getContext(), "insert barang berhasil", Toast.LENGTH_SHORT).show();
        }

    }
}
