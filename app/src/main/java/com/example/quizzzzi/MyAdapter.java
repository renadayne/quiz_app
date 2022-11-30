package com.example.quizzzzi;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    ArrayList<topic> topicArrayList;

    public MyAdapter(Context context, ArrayList<topic> topicArrayList ){
        this.context = context;
        this.topicArrayList = topicArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        topic topic = topicArrayList.get(position);
        holder.tvHeading.setText(topic.heading);
        holder.titleImage.setImageResource(topic.titleImange);
    }

    @Override
    public int getItemCount() {
        return topicArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ShapeableImageView titleImage;
        TextView tvHeading;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titleImage =itemView.findViewById(R.id.title_image);
            tvHeading = itemView.findViewById(R.id.tvHeading);

        }
    }
}

