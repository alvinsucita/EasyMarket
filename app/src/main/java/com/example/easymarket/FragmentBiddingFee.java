package com.example.easymarket;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentBiddingFee extends Fragment implements DialogBiddingFee.BidDialogListener {

    RecyclerView rvBF;
    AdapterLelangBF adapterBF;
    ArrayList<ClassBarang> list_barang;
    ArrayList<BFproperty> list_barang2;

    ArrayList<_BarangBF> list_barang3;
    _AppDatabase db;
    int update_idx = -1;
    public FragmentBiddingFee() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_bidding_fee, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        db= Room.databaseBuilder(getContext(),_AppDatabase.class,"BarangDB").build();
        ClassBarang temp1;
        BFproperty temp2;

        list_barang = new ArrayList<ClassBarang>();
        list_barang2 = new ArrayList<BFproperty>();
        list_barang3 = new ArrayList<_BarangBF>();

        getAllBarang();
        rvBF = view.findViewById(R.id.rvBiddingFee);
        GradientDrawable drawable3 = new GradientDrawable();
        drawable3.setShape(GradientDrawable.RECTANGLE);
        drawable3.setCornerRadius(25);
        drawable3.setStroke(8, Color.LTGRAY);
        drawable3.setColor(Color.WHITE);
        rvBF.setBackground(drawable3);
        rvBF.setLayoutManager(new LinearLayoutManager(getContext()));

//        adapterBF = new AdapterLelangBF(list_barang, list_barang2);
        adapterBF = new AdapterLelangBF(list_barang3);
        adapterBF.setOnItemClickListener(new AdapterLelangBF.OnItemClickListener() {
            @Override
            public void onButtonClick(String position, View view) {
                openDialog(Integer.parseInt(position));
            }
        });
        rvBF.setAdapter(adapterBF);
        adapterBF.notifyDataSetChanged();
    }

    public void getAllBarang(){
        new getAllBarang().execute();
    }

    private class getAllBarang extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            list_barang3.clear();
            list_barang3.addAll(db.barangDAO().getAllBarang());
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            adapterBF.notifyDataSetChanged();
        }
    }

    private class UpdateBarang extends AsyncTask<_BarangBF,Void,Void> {

        @Override
        protected Void doInBackground(_BarangBF... barangs) {
            db.barangDAO().update(barangs[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(getContext(), "update bid berhasil", Toast.LENGTH_SHORT).show();
        }
    }

    public void openDialog(int idx)
    {
        update_idx = idx;
        DialogBiddingFee dialog = new DialogBiddingFee();
        dialog.show(getFragmentManager(),"example");
    }

    @Override
    public void returnData(int nominal) {
        list_barang3.get(update_idx).setHighest_bid(nominal);
        new UpdateBarang().execute(list_barang3.get(update_idx));
    }

    public class BFproperty {
        String waktu;
        int highest_bid;

        public BFproperty(String waktu, int highest_bid) {
            this.waktu = waktu;
            this.highest_bid = highest_bid;
        }

        public String getWaktu() {
            return waktu;
        }

        public void setWaktu(String waktu) {
            this.waktu = waktu;
        }

        public int getHighest_bid() {
            return highest_bid;
        }

        public void setHighest_bid(int highest_bid) {
            this.highest_bid = highest_bid;
        }
    }
}
