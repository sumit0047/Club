package com.club.club;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Events_Adapter extends RecyclerView.Adapter<Events_Adapter.ViewHolder> {


    private Context mContext;
    private ArrayList<ModelEvent> mlist;

    Events_Adapter(Context context, ArrayList<ModelEvent> list){
        mContext = context;
        mlist = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.rv_events,parent,false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ModelEvent eventitem = mlist.get(position);
        ImageView image = holder.itemImage;
        TextView text = holder.itemTitle;

        image.setImageResource(eventitem.getImage());
        text.setText(eventitem.getTitle());
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView itemImage;
        TextView itemTitle;



        public ViewHolder(View itemView){
            super(itemView);

            itemImage = itemView.findViewById(R.id.coverImageView);
            itemTitle = itemView.findViewById(R.id.titleTextView);

        }
    }
}
