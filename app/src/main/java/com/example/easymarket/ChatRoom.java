package com.example.easymarket;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ChatRoom extends AppCompatActivity {
    TextView satu,dua,tiga;
    Button send;
    EditText pesan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        satu=findViewById(R.id.textView2);
        dua=findViewById(R.id.textView3);
        tiga=findViewById(R.id.textView4);
        send=findViewById(R.id.btnSend);
        pesan=findViewById(R.id.editText);

        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(Color.WHITE);
        drawable.setShape(GradientDrawable.OVAL);
        drawable.setStroke(5, Color.BLACK);
        send.setBackground(drawable);

        GradientDrawable drawable2 = new GradientDrawable();
        drawable2.setColor(Color.WHITE);
        drawable2.setShape(GradientDrawable.RECTANGLE);
        drawable2.setStroke(5, Color.BLACK);
        drawable2.setCornerRadius(15);
        satu.setBackground(drawable2);

        GradientDrawable drawable3 = new GradientDrawable();
        drawable3.setShape(GradientDrawable.RECTANGLE);
        drawable3.setColor(Color.parseColor("#BAFF4007"));
        drawable3.setStroke(5, Color.BLACK);
        drawable3.setCornerRadius(15);
        dua.setBackground(drawable3);

        GradientDrawable drawable4 = new GradientDrawable();
        drawable4.setColor(Color.WHITE);
        drawable4.setShape(GradientDrawable.RECTANGLE);
        drawable4.setStroke(5, Color.BLACK);
        drawable4.setCornerRadius(15);
        tiga.setBackground(drawable4);

        GradientDrawable drawable5 = new GradientDrawable();
        drawable5.setColor(Color.WHITE);
        drawable5.setShape(GradientDrawable.RECTANGLE);
        drawable5.setStroke(5, Color.BLACK);
        pesan.setBackground(drawable5);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id== android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
