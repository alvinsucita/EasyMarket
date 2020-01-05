package com.example.easymarket;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.easymarket.Config.Config;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;

import java.math.BigDecimal;
import java.util.ArrayList;

public class ActivityPembayaran extends AppCompatActivity {


    ArrayList<ClassNota> listClassNota = new ArrayList<>();
    Button bt;
    EditText et;
    String amount;
    private static final int PAYPAL_REQUEST_CODE = 7171;
    private static PayPalConfiguration config = new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId(Config.PAYPAL_CLIENT_ID);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran);

        bt=findViewById(R.id.button3);
        et=findViewById(R.id.editText3);

        GradientDrawable drawable2 = new GradientDrawable();
        drawable2.setShape(GradientDrawable.RECTANGLE);
        drawable2.setCornerRadius(50);
        drawable2.setColor(Color.WHITE);
        et.setBackground(drawable2);

        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setCornerRadius(100);
        drawable.setColor(Color.WHITE);
        bt.setBackground(drawable);

        Intent intent = new Intent(this, PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        startService(intent);

        Intent i = getIntent();
        listClassNota = (ArrayList<ClassNota>) i.getSerializableExtra("nota");

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nominal = Integer.parseInt(et.getText().toString());
                if(nominal==listClassNota.get(0).total){
                    processPayment();
                }
                else{
                    Toast.makeText(ActivityPembayaran.this, "Nominal yang anda masukkan tidak sesuai total transaksi", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public void onDestroy() {
        stopService(new Intent(this, PayPalService.class));
        super.onDestroy();
    }

    private void processPayment() {
        amount = et.getText().toString();
        int convert = Integer.parseInt(amount)/14000;
        PayPalPayment payment = new PayPalPayment(new BigDecimal(Integer.valueOf(convert)), "USD", "BAYAR", PayPalPayment.PAYMENT_INTENT_SALE);
        Intent i = new Intent(this, PaymentActivity.class);
        i.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        i.putExtra(com.paypal.android.sdk.payments.PaymentActivity.EXTRA_PAYMENT, payment);
        startActivityForResult(i, PAYPAL_REQUEST_CODE);
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
                    Toast.makeText(this, "Pembayaran anda berhasil,silahkan tunggu konfirmasi dari toko yang bersangkutan", Toast.LENGTH_SHORT).show();

                    final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("ClassNota");
                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Boolean cek = true;
                            for (DataSnapshot ds:dataSnapshot.getChildren()){
                                ClassNota updatenota = new ClassNota();
                                updatenota.setIdnota(ds.child("idnota").getValue().toString());
                                updatenota.setNamatoko(ds.child("namatoko").getValue().toString());
                                updatenota.setIdbarang(ds.child("idbarang").getValue().toString());
                                updatenota.setPosisi(2);
                                updatenota.setNamauser(ds.child("namauser").getValue().toString());
                                updatenota.setAlamat(ds.child("alamat").getValue().toString());
                                updatenota.setPembayaran(ds.child("pembayaran").getValue().toString());
                                updatenota.setJenispengiriman(ds.child("jenispengiriman").getValue().toString());
                                updatenota.setHargabarang(Integer.parseInt(ds.child("hargabarang").getValue().toString()));
                                updatenota.setJumlahbarang(Integer.parseInt(ds.child("jumlahbarang").getValue().toString()));
                                updatenota.setHargapengiriman(Integer.parseInt(ds.child("hargapengiriman").getValue().toString()));
                                updatenota.setTotal(Integer.parseInt(ds.child("total").getValue().toString()));

                                databaseReference.child(ds.getKey()).setValue(updatenota);
                            }
                            Intent i = new Intent(ActivityPembayaran.this,HistoryActivity.class);
                            startActivity(i);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    });
                }
            }
            else if(resultCode == com.paypal.android.sdk.payments.PaymentActivity.RESULT_EXTRAS_INVALID)
            {
                Toast.makeText(this, "Invalid", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
