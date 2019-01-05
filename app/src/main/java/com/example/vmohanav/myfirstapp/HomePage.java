package com.example.vmohanav.myfirstapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

import in.goodiebag.carouselpicker.CarouselPicker;

public class HomePage extends AppCompatActivity {

    CarouselPicker homepagecarouselpicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);



        homepagecarouselpicker = (CarouselPicker) findViewById(R.id.homepagecarouselpicker);
        List<CarouselPicker.PickerItem> itemsImages = new ArrayList<>();
        //itemsImages.add(new CarouselPicker.DrawableItem(R.mipmap.h1));
        //itemsImages.add(new CarouselPicker.DrawableItem(R.mipmap.h2));
        //itemsImages.add(new CarouselPicker.DrawableItem(R.mipmap.h3));
        itemsImages.add(new CarouselPicker.DrawableItem(R.mipmap.h5));

        CarouselPicker.CarouselViewAdapter imageAdapter= new CarouselPicker.CarouselViewAdapter(this,itemsImages,0);
        homepagecarouselpicker.setAdapter(imageAdapter);
        //ImageButton myButton = (ImageButton) findViewById(R.id.patientRetrival);
    }
    public void submitLepra(View button){
        Intent intent = new Intent(this, TabExample.class);
        startActivity(intent);
    }


    public void submitCare(View button){

    }


}
