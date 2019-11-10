package com.club.club;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class Event_Details extends AppCompatActivity {

    DatabaseReference mRef;
    private TextView clubname,eventname,eventtime,eventvenue,eventfee,eventdesc,date;
    private ImageView image;
    private CardView reg_fee_card;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event__details);

        reg_fee_card = (CardView) findViewById(R.id.reg_fee_card);
        image = (ImageView) findViewById(R.id.event_image);
        clubname = (TextView) findViewById(R.id.event_club_name);
        eventname = (TextView) findViewById(R.id.event_name);
        date = (TextView) findViewById(R.id.date);
        eventtime = (TextView) findViewById(R.id.event_time);
        eventvenue = (TextView) findViewById(R.id.event_venue);
        eventfee = (TextView) findViewById(R.id.event_reg_fee);
        eventdesc = (TextView) findViewById(R.id.event_desc);



        Intent intent = getIntent();
        String id = intent.getStringExtra("name");
        Toast.makeText(getApplicationContext(),id,Toast.LENGTH_SHORT).show();

        FirebaseDatabase.getInstance().getReference("events").orderByChild("title").equalTo(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    ModelEventDetails model = postSnapshot.getValue(ModelEventDetails.class);
                    eventname.setText(model.getTitle());
                    clubname.setText(model.getClub());
                    date.setText(model.getDate());
                    eventdesc.setText(model.getDesc());
                    eventfee.setText(model.getRegfee());
                    eventvenue.setText(model.getVenue());
                    eventtime.setText(model.getTime());
                    if(model.getRegf().equals("n"))
                        reg_fee_card.setVisibility(View.GONE);
                    Picasso.get().load(model.getImage()).into(image, new Callback() {
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError(Exception e) {
                            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }

}
