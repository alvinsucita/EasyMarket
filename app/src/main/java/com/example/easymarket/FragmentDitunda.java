package com.example.easymarket;


import android.content.Intent;
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

import com.example.easymarket.Config.Config;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;

import java.math.BigDecimal;

import static android.app.Activity.RESULT_OK;


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


    Button bt;
    EditText et;
    String amount;

    private static final int PAYPAL_REQUEST_CODE = 7171;
    private static PayPalConfiguration config = new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId(Config.PAYPAL_CLIENT_ID);

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bt = view.findViewById(R.id.button3);
        et = view.findViewById(R.id.editText3);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processPayment();
            }
        });

        Intent intent = new Intent(this.getContext(), PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        getActivity().startService(intent);
    }

    @Override
    public void onDestroy() {
        getActivity().stopService(new Intent(this.getContext(), PayPalService.class));
        super.onDestroy();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PAYPAL_REQUEST_CODE){
            if(resultCode == RESULT_OK){
                PaymentConfirmation confirm = data.getParcelableExtra(com.paypal.android.sdk.payments.PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                if(confirm == null){
                    try {
                        String getDetails = confirm.toJSONObject().toString(4);
                        startActivity(new Intent(this.getContext(), PaymentDetails.class)
                                .putExtra("PaymentDetails", getDetails)
                                .putExtra("PaymentAmount", amount));
                    } catch (JSONException e){
                        e.printStackTrace();
                    }
                }
                else {
//                    Toast.makeText(this.getContext(), "Cancel", Toast.LENGTH_SHORT).show();

                }
            }
            else if(resultCode == com.paypal.android.sdk.payments.PaymentActivity.RESULT_EXTRAS_INVALID)
            {
                Toast.makeText(this.getContext(), "Invalid", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void processPayment() {
        amount = et.getText().toString();
        PayPalPayment payment = new PayPalPayment(new BigDecimal(Integer.valueOf(amount)), "USD", "BAYAR", PayPalPayment.PAYMENT_INTENT_SALE);
        Intent i = new Intent(this.getContext(), PaymentActivity.class);
        i.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        i.putExtra(com.paypal.android.sdk.payments.PaymentActivity.EXTRA_PAYMENT, payment);
        startActivityForResult(i, PAYPAL_REQUEST_CODE);
    }
}
