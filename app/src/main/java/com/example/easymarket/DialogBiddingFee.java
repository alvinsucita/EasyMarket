package com.example.easymarket;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class DialogBiddingFee extends AppCompatDialogFragment {

    Button btn_bid;
    EditText et_nominal;
    BidDialogListener dialogListener;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("set bidding fee");
        LayoutInflater lf = getActivity().getLayoutInflater();
        View v1 = lf.inflate(R.layout.dialog_bidding_fee, null);
        builder.setView(v1);

        et_nominal = v1.findViewById(R.id.biddingFee_editText_nominal);
        btn_bid = v1.findViewById(R.id.biddingFee_button_dialog_submit);
        btn_bid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                dialogListener.returnData(Integer.parseInt(et_nominal.getText().toString()));
            }
        });
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
//        dialogListener = (BidDialogListener) context;
    }

    public interface BidDialogListener {
        void returnData(int nominal);
    }
}
