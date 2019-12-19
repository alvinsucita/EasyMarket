package com.example.easymarket;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.easymarket.Config.Config;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;

import java.math.BigDecimal;


public class PreviewActivity extends AppCompatActivity {

    private static final int PAYPAL_REQUEST_CODE = 7171;
    private static PayPalConfiguration config = new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId(Config.PAYPAL_CLIENT_ID);

    Button btnBack;
    Button bt;
    EditText et;
    String amount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        btnBack = findViewById(R.id.buttonBack);

        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setStroke(5, Color.BLACK);
        drawable.setCornerRadius(15);
        btnBack.setBackground(drawable);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PreviewActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processPayment();
            }
        });

        Intent intent = new Intent(this, PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        startService(intent);
    }
    @Override
    public void onDestroy() {
        stopService(new Intent(this, PayPalService.class));
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
                        startActivity(new Intent(this, PaymentDetails.class)
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
                Toast.makeText(this, "Invalid", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void processPayment() {
        amount = et.getText().toString();
        PayPalPayment payment = new PayPalPayment(new BigDecimal(Integer.valueOf(amount)), "USD", "BAYAR", PayPalPayment.PAYMENT_INTENT_SALE);
        Intent i = new Intent(this, PaymentActivity.class);
        i.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        i.putExtra(com.paypal.android.sdk.payments.PaymentActivity.EXTRA_PAYMENT, payment);
        startActivityForResult(i, PAYPAL_REQUEST_CODE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
