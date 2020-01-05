package com.example.easymarket;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class PaymentDetails extends AppCompatActivity {

    TextView txtid, txtamount, txtstatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_details);
        txtid = findViewById(R.id.txtID);
        txtamount = findViewById(R.id.txtAmount);
        txtstatus = findViewById(R.id.txtStatus);

        Intent i = getIntent();
        try{
            JSONObject jsonObject = new JSONObject(i.getStringExtra("PaymentDetails"));
            showDetails(jsonObject.getJSONObject("response"), i.getStringExtra("PaymentAmount"));
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }

    private void showDetails(JSONObject response, String paymentAmount) {
        try{
            txtid.setText(response.getString("id"));
            txtstatus.setText(response.getString("state"));
            txtamount.setText(response.getString(String.format("Rp%s", paymentAmount)));
        } catch (JSONException e){
            e.printStackTrace();
        }
    }
}