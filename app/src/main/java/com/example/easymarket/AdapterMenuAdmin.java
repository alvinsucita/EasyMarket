package com.example.easymarket;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AdapterMenuAdmin extends ArrayAdapter {
    private final Activity context;
    private final String[] judul;
    public AdapterMenuAdmin(Activity context, String[] judul){
        super(context,R.layout.item_textonlyadmin, judul);
        this.context = context;
        this.judul = judul;
    }
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.item_textonlyadmin, null,true);
        TextView nameTextField = (TextView) rowView.findViewById(R.id.layoutjudul);
        TextView infoTextField = (TextView) rowView.findViewById(R.id.layoutdesc);
        nameTextField.setText(judul[position]);
        infoTextField.setText("");
        return rowView;
    };
}