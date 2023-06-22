package com.example.aexpress.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;

import com.example.aexpress.R;
import com.example.aexpress.databinding.ActivityCartBinding;

public class CartActivity extends AppCompatActivity {

    //59.
    ActivityCartBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //60.
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //for 61. go in res/drawable/menu/caer.xml//
    }
}