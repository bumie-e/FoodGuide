package com.bumie.foodguide;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecylerViewAdapter extends RecyclerView.Adapter<RecylerViewAdapter.MyViewHolder> {

    private List<Places> itemsList;

    RecylerViewAdapter(List<Places> mItemList){
        this.itemsList = mItemList;
    }

    @NonNull
    @Override
    public RecylerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_food_places,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecylerViewAdapter.MyViewHolder holder, int position) {
        final Places item = itemsList.get(position);
        holder.name.setText(item.getName());
        holder.resturants.setText(String.valueOf(item.getPrice()));
        holder.resturants.setText(String.valueOf(item.getPrice()));
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView name,resturants;
        ImageView image;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView);
            resturants = itemView.findViewById(R.id.textView2);
            image =  itemView.findViewById(R.id.imageView);
        }
    }
}
