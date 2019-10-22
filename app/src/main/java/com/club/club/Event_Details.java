package com.club.club;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;

public class Event_Details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event__details);

        CardView reg_fee_card = (CardView) findViewById(R.id.reg_fee_card);
        //reg_fee_card.setVisibility(View.GONE);
    }
}
