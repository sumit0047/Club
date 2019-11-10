package com.club.club;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class Clubs extends Fragment {

    RecyclerView recyclerView;

    DatabaseReference mRef;
    FirebaseRecyclerOptions<ModelClub> options;
    FirebaseRecyclerAdapter<ModelClub,ClubViewHolder> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_clubs, container, false);

        recyclerView = view.findViewById(R.id.rv_c);

        mRef = FirebaseDatabase.getInstance().getReference().child("clubs");

        options = new FirebaseRecyclerOptions.Builder<ModelClub>()
                .setQuery(mRef,ModelClub.class).build();

        adapter = new FirebaseRecyclerAdapter<ModelClub, ClubViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ClubViewHolder holder, int position, @NonNull ModelClub model) {

                Picasso.get().load(model.getImage()).into(holder.i1, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();

                    }
                });

                holder.t1.setText(model.getTitle());

            }

            @NonNull
            @Override
            public ClubViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_clubs,parent,false);
                return new ClubViewHolder(view);
            }
        };

        recyclerView.addOnItemTouchListener(new RvItemClickListener(getContext(), recyclerView, new RvItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getContext(), "Clicked " +position, Toast.LENGTH_SHORT).show();
                View view2 = recyclerView.getLayoutManager().findViewByPosition(position);
                TextView tv = (TextView)view2.findViewById(R.id.rv_club_title);
                Intent intent = new Intent(getContext(), Club_Details.class);
                intent.putExtra("name",tv.getText().toString());
                startActivity(intent);

            }

            @Override
            public void onLongItemClick(View view, int position) {
                Toast.makeText(getContext(), "long Clicked", Toast.LENGTH_SHORT).show();
            }
        }));




        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        RecyclerView.LayoutManager rvlayoutmanager = layoutManager;

        recyclerView.setLayoutManager(rvlayoutmanager);

        adapter.startListening();

        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if(adapter!=null)
            adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        if(adapter!=null)
            adapter.stopListening();
    }

    @Override
    public void onResume() {
        super.onResume();
        if(adapter!=null)
            adapter.startListening();
    }

}
