package com.club.club;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;


public class Events extends Fragment{

    RecyclerView recyclerView;
    ArrayList<ModelEvent> meventlist;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_events, container, false);

        recyclerView = view.findViewById(R.id.rv);

        recyclerView.addOnItemTouchListener(new RvItemClickListener(getContext(), recyclerView, new RvItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getContext(), "Clicked " +position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongItemClick(View view, int position) {
                Toast.makeText(getContext(), "long Clicked", Toast.LENGTH_SHORT).show();
            }
        }));

        meventlist = new ArrayList<>();

        meventlist.add(new ModelEvent(R.drawable.horiz,"Code it Out"));
        meventlist.add(new ModelEvent(R.drawable.bp,"TEDxSIT"));
        meventlist.add(new ModelEvent(R.drawable.qq,"Quriosity"));
        meventlist.add(new ModelEvent(R.drawable.qq2,"The Entertainment Quiz"));

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        RecyclerView.LayoutManager rvlayoutmanager = layoutManager;

        recyclerView.setLayoutManager(rvlayoutmanager);

        Events_Adapter eadapter = new Events_Adapter(getContext(),meventlist);

        recyclerView.setAdapter(eadapter);

        return view;


    }

}
