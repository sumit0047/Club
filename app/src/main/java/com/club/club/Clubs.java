package com.club.club;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rey.material.widget.ProgressView;
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

    private ProgressView pv_circular;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_clubs, container, false);

        recyclerView = view.findViewById(R.id.rv_c);
        pv_circular=(ProgressView)view.findViewById(R.id.club_progress);

        mRef = FirebaseDatabase.getInstance().getReference().child("clubs");

        options = new FirebaseRecyclerOptions.Builder<ModelClub>()
                .setQuery(mRef,ModelClub.class).build();

        adapter = new FirebaseRecyclerAdapter<ModelClub, ClubViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ClubViewHolder holder, int position, @NonNull ModelClub model) {

                holder.club_container.setAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.fade_transition_animation));

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

            @Override
            public void onDataChanged() {
                super.onDataChanged();
                pv_circular.setVisibility(View.GONE);
            }

            @Override
            public void onError(@NonNull DatabaseError error) {
                super.onError(error);
                Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
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
