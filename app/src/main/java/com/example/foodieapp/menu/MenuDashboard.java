package com.example.foodieapp.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.foodieapp.R;
import com.example.foodieapp.menu.burger.AddBurger;
import com.example.foodieapp.menu.pizza.AddPizza;
import com.example.foodieapp.menu.submarine.AddSubmarine;

public class MenuDashboard extends AppCompatActivity {
    private ImageView pizzaAddButton,submarineAddButton,burgeeAddButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_dashboard);
        pizzaAddButton = findViewById(R.id.pizzaAddButton);
        submarineAddButton = findViewById(R.id.submarineAddButton);
        burgeeAddButton = findViewById(R.id.burgeeAddButton);

        pizzaAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuDashboard.this, AddPizza.class);
                startActivity(i);
            }
        });
        submarineAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuDashboard.this, AddSubmarine.class);
                startActivity(i);
            }
        });
        burgeeAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuDashboard.this, AddBurger.class);
                startActivity(i);
            }
        });
    }
}
