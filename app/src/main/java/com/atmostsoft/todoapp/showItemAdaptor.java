package com.atmostsoft.todoapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class showItemAdaptor extends RecyclerView.Adapter<showItemAdaptor.ViewHolder> {

    ArrayList<showList> arrayList;
    public showItemAdaptor(ArrayList<showList> people)
    {
        arrayList = people;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvList;
        TextView tvItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvList = itemView.findViewById(R.id.listName);
            tvItem = itemView.findViewById(R.id.items);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvList.setText(arrayList.get(position).listName);
        if (arrayList.get(position).item.size() > 3)
            holder.tvItem.setText(arrayList.get(position).getItem().get(0)+","+arrayList.get(position).getItem().get(1));
        else
            holder.tvItem.setText(arrayList.get(position).getItem().get(0));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
