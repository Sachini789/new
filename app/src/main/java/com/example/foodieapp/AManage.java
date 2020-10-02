package com.example.foodieapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AManage extends AppCompatActivity {

    Button lgout,menuManage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_manage);

        lgout = findViewById(R.id.LogOut);
        menuManage = findViewById(R.id.Manage);
        lgout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AManage.this,MainActivity.class);
                setIntent(i);
            }
        });
        menuManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AManage.this,MManage.class);
                setIntent(i);
            }
        });
    }
}