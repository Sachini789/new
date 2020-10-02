package com.example.foodieapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class TopEats extends AppCompatActivity {


    ImageView imgBtnTopEats,imageTopEats,specialOffers,imageFavorites,imageOurMenu,imageDiatery,imageLatestDeals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_eats);

        imgBtnTopEats = findViewById(R.id.imageTopEats);
        imageFavorites = findViewById(R.id.imageFavorites);
        imageOurMenu = findViewById(R.id.imageOurMenu);
        imageDiatery = findViewById(R.id.imageDiatery);
        specialOffers = findViewById(R.id.specialOffers);
        imageLatestDeals = findViewById(R.id.imageLatestDeals);

        imageFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TopEats.this, Favorites.class));
            }
        });
        imageOurMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TopEats.this, OurMenu.class));
            }
        });
        imageLatestDeals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TopEats.this, LatestDeals.class));
            }
        });
        imageDiatery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TopEats.this, Diatery.class));
            }
        });
        imgBtnTopEats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TopEats.this, TopCategories.class));
            }
        });
        specialOffers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TopEats.this, SpecialOffers.class));
            }
        });


    }
}