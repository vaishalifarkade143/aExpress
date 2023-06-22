package com.example.aexpress.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.aexpress.R;
import com.example.aexpress.adapters.CategoryAdapter;
import com.example.aexpress.adapters.ProductAdapter;
import com.example.aexpress.databinding.ActivityMainBinding;
import com.example.aexpress.model.Category;
import com.example.aexpress.model.Product;
import com.example.aexpress.utils.Constants;

import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //9.
    ActivityMainBinding binding;



    //12.to set Adapter on recrecycleCategoriesList(Recycler View)
    CategoryAdapter categoryAdapter;
    //or ye multiple data lekar ayega ya fir dikhayega recycleView par esiliye
    ArrayList<Category> categories;



    //27.
    ProductAdapter productAdapter;
    ArrayList<Product> products;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //10.
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //13.call method
        initCategories();
        //28.call method
        initProducts();
        //31.
        initslider();

    }



    //13.create method
    void initCategories()
    {
        categories = new ArrayList<>();

//        categories.add(new Category("Herbs & Spices","https://www.flaticon.com/free-icon/toys_3082060","#4d77b1","some use",1));
//        categories.add(new Category("Baby image","data:image/jpeg;base64,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","#340785","some use",2));
//        categories.add(new Category("Herbs & Spices","https://w7.pngwing.com/pngs/715/843/png-transparent-toy-graphy-illustration-toys-daquan-child-baby-photography-thumbnail.png","#27BD2D","some use",3));
//        categories.add(new Category("Herbs & Spices","https://tutorials.mianasad.com/ecommerce/uploads/category/1686961645774.png","#BD4018","some use",4));
//        categories.add(new Category("Herbs & Spices","https://tutorials.mianasad.com/ecommerce/uploads/category/1686961645774.png","#0C5997","some use",5));
//        categories.add(new Category("Herbs & Spices","https://tutorials.mianasad.com/ecommerce/uploads/category/1686961645774.png","#007cff","some use",6));

        categoryAdapter  = new CategoryAdapter(this,categories);

        //33.call method
        getCategories();

        //11. we can show in GridLayout or LinearLayout
        GridLayoutManager layoutManager = new GridLayoutManager(this,4);// kitne colomn show karne hai 4
        binding.recycleCategoriesList.setLayoutManager(layoutManager);
        binding.recycleCategoriesList.setAdapter(categoryAdapter);
    }

    //32.
    void getCategories()
    {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(
                Request.Method.GET,
                Constants.GET_CATEGORIES_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Log.e("error",response);
                        //34.
                        try {
                            JSONObject mainobj = new JSONObject(response);//mainjson object
                            //agar humara json object ka status equals to success hai tabhi hum onject read karenge
                            if (mainobj.getString("status").equals("success"))
                            {
                                JSONArray categoriesArray = mainobj.getJSONArray("categories"); //json array
                                //json object
                                for (int i = 0;i< categoriesArray.length();i++)
                                {
                                    JSONObject object = categoriesArray.getJSONObject(i);
                                    Category category = new Category(
                                            object.getString("name"),
                                           Constants.CATEGORIES_IMAGE_URL +  object.getString("icon"),
                                            object.getString("color"),
                                            object.getString("brief"),
                                            object.getInt("id")
                                );
                                    //35.
                                    categories.add(category);
                                }
                                //36.add on adapter to show category
                                categoryAdapter.notifyDataSetChanged();
                            }
                            else {
                                //do nothing

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error",""+error);

                    }
                }
        );
        queue.add(request);
    }


    //28.create method
    void initProducts()
    {
        products = new ArrayList<>();
        //30. addding sample data
//        products.add(new Product("aglaonema red plant","https://tutorials.mianasad.com/ecommerce/uploads/product/1687331844878.png","",12,12,1,1));
//        products.add(new Product("aglaonema red plant","https://tutorials.mianasad.com/ecommerce/uploads/product/1687331844878.png","",12,12,1,1));
//        products.add(new Product("aglaonema red plant","https://tutorials.mianasad.com/ecommerce/uploads/product/1687331844878.png","",12,12,1,1));
//        products.add(new Product("aglaonema red plant","https://tutorials.mianasad.com/ecommerce/uploads/product/1687331844878.png","",12,12,1,1));
//        products.add(new Product("aglaonema red plant","https://tutorials.mianasad.com/ecommerce/uploads/product/1687331844878.png","",12,12,1,1));
//        products.add(new Product("aglaonema red plant","https://tutorials.mianasad.com/ecommerce/uploads/product/1687331844878.png","",12,12,1,1));

        productAdapter = new ProductAdapter(this,products);

        //41.call method
        getRecentProducts();

        //29.
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        binding.recycleProductList.setLayoutManager(layoutManager);
        binding.recycleProductList.setAdapter(productAdapter);
    }


    //37.
    void getRecentProducts()
    {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Constants.GET_PRODUCTS_URL + "?count=8";
        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                //i m using java so i can eplace this with lambda Alt Enter
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            if (object.getString("status").equals("success"))
                            {
                                JSONArray productsArray = object.getJSONArray("products");
                                for (int i=0;i<productsArray.length();i++)
                                {
                                    JSONObject childObject = productsArray.getJSONObject(i);
                                    Product  product = new Product(
                                            childObject.getString("name"),
                                            Constants.PRODUCTS_IMAGE_URL + childObject.getString("image"),
                                            childObject.getString("status"),
                                            childObject.getDouble("price"),
                                            childObject.getDouble("price_discount"),
                                            childObject.getInt("stock"),
                                            childObject.getInt("id")
                                    );
                                    //38.adding model class data to  arrayList
                                    products.add(product);
                                }
                                //39.
                                productAdapter.notifyDataSetChanged();
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
        //40.
        queue.add(request);
    }

    //31. for use of slider/carousel
    private void initslider() {
//        binding.carousel.addData(new CarouselItem("https://i.pinimg.com/736x/a8/e4/22/a8e422092e51a105cb09e09d80a7ce72.jpg","pink dress"));
//        binding.carousel.addData(new CarouselItem("https://designmodo.com/wp-content/uploads/2017/01/slider.jpg","yellow dress"));
//        binding.carousel.addData(new CarouselItem("https://www.jssor.com/premium/fashion/img/glass-woman.jpg","purple dress"));
//        binding.carousel.addData(new CarouselItem("https://www.shutterstock.com/image-vector/red-banner-special-offer-260nw-1035467014.jpg","purple dress"));

        //43. call method
        getRequestOffer();
    }


     //42. to get slider/carousel images
    void getRequestOffer()
    {
        RequestQueue queue =Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(
                Request.Method.GET,
                Constants.GET_OFFERS_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            if (object.getString("status").equals("success"))
                            {
                                JSONArray offerArray =object.getJSONArray("news_infos");
                                for (int i = 0 ; i < offerArray.length() ; i++)
                                {
                                    JSONObject childObject = offerArray.getJSONObject(i);
                                    binding.carousel.addData(
                                            new CarouselItem(
                                                    Constants.NEWS_IMAGE_URL + childObject.getString("image"),
                                                    childObject.getString("title")

                                            )
                                    );
                                }
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


}