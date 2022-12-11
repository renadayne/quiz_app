package com.example.quizzzzi;

import android.os.Bundle;

import androidx.annotation.ArrayRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quizzzzi.databinding.FragmentHomeBinding;

import java.util.ArrayList;


public class homeFragment extends Fragment {
    FragmentHomeBinding binding;

//    private ArrayList<topic> topicArraylist;
//    private String[] newsHeading;
//    private int[] imageResouceID;
//    private RecyclerView recyclerview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding =FragmentHomeBinding.inflate(inflater,container, false);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerview.setAdapter(new MyAdapter(getContext(), topic.list));
        return binding.getRoot();
    }



}