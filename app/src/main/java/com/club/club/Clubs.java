package com.club.club;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class Clubs extends Fragment {

    RecyclerView recyclerView;
    ArrayList<ModelClub> mclublist;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_clubs, container, false);

        recyclerView = view.findViewById(R.id.rv_c);


        mclublist = new ArrayList<>();

        mclublist.add(new ModelClub(R.drawable.sit,"Siddaganga Institute of Technology"));
        mclublist.add(new ModelClub(R.drawable.dmc16,"Destiny Music Clique"));
        mclublist.add(new ModelClub(R.drawable.quiz_logo,"Quizzing Inc."));


        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        RecyclerView.LayoutManager rvlayoutmanager = layoutManager;

        recyclerView.setLayoutManager(rvlayoutmanager);

        Clubs_Adapter cadapter = new Clubs_Adapter(getContext(),mclublist);

        recyclerView.setAdapter(cadapter);

        return view;
    }

}
