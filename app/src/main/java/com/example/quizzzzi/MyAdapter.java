package com.example.quizzzzi;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class    MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
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
        topic item = topicArrayList.get(position);
        holder.tvHeading.setText(item.getHeading());
        holder.titleImage.setImageResource(item.getTitleImange());

        holder.itemView.setOnClickListener(view -> {
            // save lai topic
            if(holder.getAdapterPosition()==0){
                save.setTopic("history");
                save.setImage(R.drawable.history);
            }else if(holder.getAdapterPosition()==1){
                save.setTopic("literature");
                save.setImage(R.drawable.literatue);
            }else if(holder.getAdapterPosition()==2){
                save.setTopic("science");
                save.setImage(R.drawable.science);
            }else if(holder.getAdapterPosition()==3){
                save.setTopic("math");
                save.setImage(R.drawable.math);}

            AppCompatActivity activity =(AppCompatActivity)view.getContext();
            levelFragment levelFragment = new levelFragment();
            activity.getSupportFragmentManager().beginTransaction().replace(R.id.container,levelFragment).addToBackStack(null).commit();
            Toast.makeText(view.getContext(), save.getTopic(), Toast.LENGTH_SHORT).show();
        });
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

