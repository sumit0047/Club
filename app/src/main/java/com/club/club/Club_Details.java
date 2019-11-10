package com.club.club;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;


public class Club_Details extends AppCompatActivity {

    private TextView clubname,clubmem,clubestd,clubdesc,cont1,cont2,contn1,contn2;
    private ImageView clublogo;


    CarouselView carouselView;
    String[] img;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club__details);



        img=new String[5];
        clubestd = (TextView)findViewById(R.id.club_estd);
        clubmem = (TextView)findViewById(R.id.club_mem);
        clubdesc = (TextView)findViewById(R.id.club_desc);
        clubname = (TextView)findViewById(R.id.club_name);
        cont1 = (TextView)findViewById(R.id.club_contact1);
        cont2 = (TextView)findViewById(R.id.club_contact2);
        contn1 = (TextView)findViewById(R.id.club_contact_name1);
        contn2 = (TextView)findViewById(R.id.club_contact_name2);
        clublogo = (ImageView)findViewById(R.id.club_logo);

        Intent intent = getIntent();
        String id = intent.getStringExtra("name");
        Toast.makeText(getApplicationContext(),id,Toast.LENGTH_SHORT).show();

        FirebaseDatabase.getInstance().getReference("clubs").orderByChild("title").equalTo(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    ModelClubDetails model = postSnapshot.getValue(ModelClubDetails.class);

                    clubname.setText(model.getTitle());
                    clubdesc.setText(model.getDesc());
                    clubestd.setText(model.getEstd());
                    clubmem.setText(model.getMemc());
                    cont1.setText(model.getContact1());
                    contn1.setText(model.getContact1n());
                    cont2.setText(model.getContact2());
                    contn2.setText(model.getContact2n());
                    Picasso.get().load(model.getImage()).into(clublogo, new Callback() {
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError(Exception e) {
                                Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });
                    img[0]=model.getPic1();
                    img[1]=model.getPic2();
                    img[2]=model.getPic3();
                    img[3]=model.getPic4();
                    img[4]=model.getPic5();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        carouselView = (CarouselView) findViewById(R.id.carouselView);
        carouselView.setImageListener(imageListener);
        carouselView.setPageCount(5);


    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            Picasso.get().load(img[position]).fit().centerCrop().into(imageView);

        }
    };


    @Override
    protected void onStop() {
        super.onStop();
        for(int i=0;i<5;i++)
            Picasso.get().invalidate(img[i]);
    }
}
