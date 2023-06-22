package com.example.aexpress.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.aexpress.R;
import com.example.aexpress.activities.ProductDetailsActivity;
import com.example.aexpress.databinding.ItemProductBinding;
import com.example.aexpress.model.Product;

import java.util.ArrayList;

//14.created java class
                                //18.extends RecyclerView.Adapter<ProductAdapter.ProductViewAdapter>
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>
{
    //19.
    Context context;
    ArrayList<Product> products;

    //20.constructor
    public ProductAdapter(Context context,ArrayList<Product> products)
    {
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //21.
        return new ProductViewHolder(LayoutInflater.from(context).inflate(R.layout.item_product,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        //23. binding ke andar us product ko get kar liya
        Product product = products.get(position);
        //24.geting image from internet n bind on Modelclass refernce product
        Glide.with(context)
                .load(product.getImage())
                .into(holder.binding.image);
        //25.
        holder.binding.lebel.setText(product.getName());
        holder.binding.txtPrice.setText("Rs. "+product.getPrice());//"Rs. " to concatinate n remove error
        //now go to mainActivity n use/add 2nd recyclerview

        //46.RecyclerView par itemClick Lisstner lagayenge
//        holder.itemView.setOnClickListener(c ->{
//            //using lambda
//        });
        //or
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductDetailsActivity.class);
                intent.putExtra("name",product.getName());
                intent.putExtra("image",product.getImage());
                intent.putExtra("id",product.getId());
                intent.putExtra("price",product.getPrice());
                //47.
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        //22.
        return products.size();
    }

    //15. created ProductViewAdapter to extend ViewHolder
    public class ProductViewHolder extends RecyclerView.ViewHolder
    {
     //16.
    ItemProductBinding binding;

        public ProductViewHolder(@NonNull View itemView)
        {
            super(itemView);
            //17. jub hum adapter par binding karte hai to use kare bind() or Activity par inflate use karenge
            //Activity me inflater avilable hai but Adapter me nhi hai to hum   -- View itemView    -- se bind karte hai
            binding = ItemProductBinding.bind(itemView);

        }
    }
}
