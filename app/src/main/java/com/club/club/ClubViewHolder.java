package com.club.club;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ClubViewHolder extends RecyclerView.ViewHolder {

    public TextView t1;
    public ImageView i1;

    public ClubViewHolder(View itemView) {
        super(itemView);

        t1 = (TextView) itemView.findViewById(R.id.rv_club_title);
        i1 = (ImageView) itemView.findViewById(R.id.rv_club_logo);
    }

}
