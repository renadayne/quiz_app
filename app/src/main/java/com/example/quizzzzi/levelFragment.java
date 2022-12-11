package com.example.quizzzzi;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.quizzzzi.databinding.FragmentLevelBinding;


public class levelFragment extends Fragment {
    FragmentLevelBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding =FragmentLevelBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}