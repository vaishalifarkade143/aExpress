package com.example.aexpress.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.aexpress.R;
import com.example.aexpress.databinding.ItemCategoriesBinding;
import com.example.aexpress.model.Category;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>
{
    //3.
    Context context;
    ArrayList<Category> categories;
    //4. create constructor
    public CategoryAdapter(Context context, ArrayList<Category> categories)
    {
        this.context= context;
        this.categories = categories;
    }



    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //5. pasing/using sample View i.e item_categories
        return new CategoryViewHolder(LayoutInflater.from(context).inflate(R.layout.item_categories,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {

        //6. bind karana hai to Category ka obj/ref means jis position par hai
        Category category= categories.get(position);
        holder.binding.lebel.setText(category.getName());//if html tag come in text then use -->  holder.binding.lebel.setText(Html.fromHtml(category.getName()));

        //7. humne images ko model class me string me rakha hai to hum use directly access nhi kar sakte online backend se to hum Glied Librari ka use karet hai
        Glide.with(context)
                .load(category.getIcon())
                .into(holder.binding.imgeV);

        //8. to change the background of image
        //but humne Category Model class me color ko string define kiya to vo aayrga #hsdkhc aise kuch value me to hum color ko parse karenge ...Color.parseColor ye ek class hai jo String color ko integer colot me convert kar dega
        holder.binding.imgeV.setBackgroundColor(Color.parseColor(category.getColor()));

    }

    @Override
    public int getItemCount() {
        //8.
        return categories.size();
    }

    //1. create viewholder
    //if we using sample layout which has no java(Activity) file then we have to create one model class n one adapter
    public  class CategoryViewHolder extends RecyclerView.ViewHolder
    {

        //2. category binding
        ItemCategoriesBinding binding;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemCategoriesBinding.bind(itemView); //end of 2....//
        }
    }
}
