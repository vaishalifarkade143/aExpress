package com.example.aexpress.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.aexpress.R;
import com.example.aexpress.databinding.ActivityProductDetailsBinding;
import com.example.aexpress.utils.Constants;

import org.json.JSONException;
import org.json.JSONObject;

public class ProductDetailsActivity extends AppCompatActivity {
    //44.
    ActivityProductDetailsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //45.
        binding = ActivityProductDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //48.on click of product we come in productActivity n see the product detail
        String name = getIntent().getStringExtra("name");
        String image = getIntent().getStringExtra("image");
        int id = getIntent().getIntExtra("id",0);
        double price = getIntent().getDoubleExtra("price",0);

        //49. to load image from internet
        Glide.with(this)
                .load(image)
                .into(binding.imgVProductimage);//now go to themes for // 50.

        //58.call method
        getProductDetails(id);

        //55. to set text on ActionBar
        getSupportActionBar().setTitle(name);

        //52.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    //62. use cart
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cart,menu);
        return super.onCreateOptionsMenu(menu);
    }

    //63.cart item select hone k bad
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if(item.getItemId() == R.id.cart)
        {
            startActivity(new Intent(this, CartActivity.class));//ProductDetailActivity se CartActivity par jana hai 
        }

        return super.onOptionsItemSelected(item);
    }

    //57.created method
    void getProductDetails(int id ) // we want id of that specific product to show proper details of that Product n avoid mismatch
    {
        String url =  Constants.GET_PRODUCT_DETAILS_URL + id;
        //now creating Volley Request
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);//jsonObject
                            if(object.getString("status").equals("success"))
                            {
                                JSONObject productObj = object.getJSONObject("product");//again object
                               //productObj iss jsonObject se String chahiye "description"  nam ki
                                String description = productObj.getString("description");
                                binding.txtProductDecsription.setText(
                                        Html.fromHtml(description)
                                );

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        queue.add(request);
    }


    //53 . jaise hi back nevigation ko ckick karega to activity ko finish karna hai
    @Override
    public boolean onSupportNavigateUp() {
        ////54.
        finish();
        return super.onSupportNavigateUp();
    }
}