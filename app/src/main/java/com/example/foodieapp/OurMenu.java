package com.example.foodieapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodieapp.menu.pizza.Items;
import com.example.foodieapp.menu.submarine.SubmarineItems;

public class OurMenu extends AppCompatActivity {

    ImageView imgPizza,imgSubmarine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_our_menu);
        imgPizza = findViewById(R.id.imgPizza);
        imgSubmarine = findViewById(R.id.imgSubmarine);

        imgPizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OurMenu.this, Items.class));
            }
        });
        imgSubmarine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OurMenu.this, SubmarineItems.class));
            }
        });
    }
}