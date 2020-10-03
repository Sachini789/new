package com.example.foodieapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.foodieapp.menu.MenuDashboard;

public class MManage extends AppCompatActivity {
    Button addMenu,adOurMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_manage);
        addMenu = findViewById(R.id.addMenu);
        adOurMenu = findViewById(R.id.adOurMenu);
        addMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MManage.this, MenuDashboard.class);
                startActivity(i);
            }
        });
        adOurMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MManage.this, OurMenu.class);
                startActivity(i);
            }
        });
    }
}