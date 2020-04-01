package com.club.club;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class PostViewHolder extends RecyclerView.ViewHolder {

    public TextView date;
    public TextView time;
    public TextView club;
    public TextView desc;
    public ImageView img;

    public PostViewHolder(View itemView) {
        super(itemView);

        date=(TextView)itemView.findViewById(R.id.date_post);
        time=(TextView) itemView.findViewById(R.id.time_post);
        club=(TextView) itemView.findViewById(R.id.club_post);
        desc=(TextView) itemView.findViewById(R.id.desc_post);
        img=(ImageView) itemView.findViewById(R.id.img_post);
    }
}
