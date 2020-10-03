package com.example.foodieapp.menu.pizza;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.foodieapp.R;
import com.example.foodieapp.adapter.PizzaAdapter;
import com.example.foodieapp.model.Pizza;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;


public class Items extends AppCompatActivity implements PizzaAdapter.OnItemClickListener{
    private RecyclerView mRecyclerView;
    private PizzaAdapter mAdapter;
    private ProgressBar mProgressBar;
    private FirebaseStorage mStorage;
    private DatabaseReference mDatabaseRef;
    private ValueEventListener mDBListener;
    private List<Pizza> mPizza;
    private void openDetailActivity(String[] data){
        Intent intent = new Intent(this, UpdatePizza.class);
        intent.putExtra("NAME_KEY",data[0]);
        intent.putExtra("PRICE_Key",data[1]);
        intent.putExtra("SIZE_KEY",data[2]);
        intent.putExtra("URL_KEY",data[3]);
        startActivity(intent);
    }

    private void updateActivity(String[] data){
        Intent intent = new Intent(this, UpdatePizza.class);
        intent.putExtra("ID_KEY",data[0]);
        intent.putExtra("NAME_KEY",data[1]);
        intent.putExtra("PRICE_Key",data[2]);
        intent.putExtra("SIZE_KEY",data[3]);
        intent.putExtra("URL_KEY",data[4]);

        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_pizza_items);
        mRecyclerView = findViewById(R.id.mRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mProgressBar = findViewById(R.id.myDataLoaderProgressBar);
        mProgressBar.setVisibility(View.VISIBLE);
        mPizza = new ArrayList<> ();
        mAdapter = new PizzaAdapter (Items.this, mPizza);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(Items.this);
        mStorage = FirebaseStorage.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("pizza");
        mDBListener = mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mPizza.clear();
                for (DataSnapshot pizzaSnap : dataSnapshot.getChildren()) {
                    Pizza upload = pizzaSnap.getValue(Pizza.class);
                    upload.setKey(pizzaSnap.getKey());
                    mPizza.add(upload);
                }
                mAdapter.notifyDataSetChanged();
                mProgressBar.setVisibility(View.GONE);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Items.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressBar.setVisibility(View.INVISIBLE);
            }
        });
    }
    public void onItemClick(int position) {
        Pizza clickedTeacher=mPizza.get(position);
        String[] teacherData={clickedTeacher.getName(),
                clickedTeacher.getPrice(),
                clickedTeacher.getSize()
        };

        //openDetailActivity(teacherData);
    }
    @Override
    public void onShowItemClick(int position) {
        Pizza clickedTeacher=mPizza.get(position);
        final String selectedKey = clickedTeacher.getKey();
        String[] teacherData={
                selectedKey,
                clickedTeacher.getName(),
                clickedTeacher.getPrice(),
                clickedTeacher.getSize(),
                clickedTeacher.getUrl()
        };
       updateActivity(teacherData);
    }
    @Override
    public void onDeleteItemClick(int position) {
        Pizza selectedItem = mPizza.get(position);
        final String selectedKey = selectedItem.getKey();
        StorageReference imageRef = mStorage.getReferenceFromUrl(selectedItem.getUrl());
        imageRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                mDatabaseRef.child(selectedKey).removeValue();
                Toast.makeText(Items.this, "Item deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }
    protected void onDestroy() {
        super.onDestroy();
        mDatabaseRef.removeEventListener(mDBListener);
    }
}