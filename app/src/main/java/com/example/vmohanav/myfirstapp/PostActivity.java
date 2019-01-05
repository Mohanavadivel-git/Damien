package com.example.vmohanav.myfirstapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PostActivity extends AppCompatActivity {

    String url = "https://webapp-181209061846.azurewebsites.net/blog/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        Log.e("request:","request started");
        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
        JSONObject json = new JSONObject();
        try {
            json.put("title","android");
            json.put("content","android");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest  = new JsonObjectRequest (Request.Method.POST, url, json, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //This code is executed if the server responds, whether or not the response contains data.
                //The String 'response' contains the server's response.
                Log.e("request:","correct response");
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Profile updated successfully",
                        Toast.LENGTH_SHORT);
                toast.show();
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                //This code is executed if there is an error.
                Log.e("request:","Incorrect response");
                Toast toast = Toast.makeText(getApplicationContext(),
                        error.toString(),
                        Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        //};
        //jsonObjectRequest.setTag(REQ_TAG);
        MyRequestQueue.add(jsonObjectRequest );
    }
}

