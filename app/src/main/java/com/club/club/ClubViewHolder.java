package com.club.club;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ClubViewHolder extends RecyclerView.ViewHolder {

    public TextView t1;
    public ImageView i1;
    CardView club_container;

    public ClubViewHolder(View itemView) {
        super(itemView);
        club_container=(CardView)  itemView.findViewById(R.id.club_card);
        t1 = (TextView) itemView.findViewById(R.id.rv_club_title);
        i1 = (ImageView) itemView.findViewById(R.id.rv_club_logo);
    }

}
