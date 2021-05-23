package com.atmostsoft.todoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class itemAdaptor extends RecyclerView.Adapter<itemAdaptor.ViewHolder> {

    ArrayList<String> items;

    public itemAdaptor(Context context,ArrayList<String> list)
    {
        items = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvItem = itemView.findViewById(R.id.itemName);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvItem.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
