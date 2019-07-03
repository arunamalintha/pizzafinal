package com.example.pizzafinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static final String PRODUCT_URL="http://192.168.43.87:8080/demo/all/";


    RecyclerView recyclerView;
    ProductAdapter adapter;
    List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productList = new ArrayList<>();

        recyclerView=(RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadProducts();


    }

    private void  loadProducts() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://192.168.43.87:8080/demo/all",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity.this,response,Toast.LENGTH_LONG).show();
                        try {
                            JSONArray products = new JSONArray(response);

                            for (int i = 0; i < products.length(); i++) {

                                JSONObject productobject = products.getJSONObject(i);

                                int id = productobject.getInt("pizzaId");
                                String name = productobject.getString("name");
                                String shortdesc = productobject.getString("discription");
                                double price = productobject.getDouble("price");
                                double pizzaid=0;
                                String imageUrl = productobject.getString("imageUrl");



                                Product product = new Product(id,name, shortdesc, price, pizzaid, imageUrl);
                                productList.add(product);
                            }
                            adapter = new ProductAdapter(MainActivity.this, productList) {
                                @Override
                                public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

                                }
                            };
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap params = new HashMap<>();
                return params;
            }

        };


        Volley.newRequestQueue(this).add(stringRequest);
    }
}
