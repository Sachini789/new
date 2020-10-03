package com.example.foodieapp.menu.submarine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.foodieapp.R;
import com.example.foodieapp.adapter.SubmarineAdapter;
import com.example.foodieapp.menu.pizza.UpdatePizza;
import com.example.foodieapp.model.Submarine;
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

public class SubmarineItems extends AppCompatActivity implements SubmarineAdapter.OnItemClickListener{
    private RecyclerView mRecyclerView;
    private SubmarineAdapter mAdapter;
    private ProgressBar mProgressBar;
    private FirebaseStorage mStorage;
    private DatabaseReference mDatabaseRef;
    private ValueEventListener mDBListener;
    private List<Submarine> mSubmarines;
    private void openDetailActivity(String[] data){
        Intent intent = new Intent(this, UpdateSubmarine.class);
        intent.putExtra("NAME_KEY",data[0]);
        intent.putExtra("PRICE_Key",data[1]);
        intent.putExtra("URL_KEY",data[2]);
        startActivity(intent);
    }

    private void updateActivity(String[] data){
        Intent intent = new Intent(this, UpdateSubmarine.class);
        intent.putExtra("ID_KEY",data[0]);
        intent.putExtra("NAME_KEY",data[1]);
        intent.putExtra("PRICE_Key",data[2]);
        intent.putExtra("URL_KEY",data[3]);

        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_submarine_items);
        mRecyclerView = findViewById(R.id.mRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mProgressBar = findViewById(R.id.myDataLoaderProgressBar);
        mProgressBar.setVisibility(View.VISIBLE);
        mSubmarines = new ArrayList<>();
        mAdapter = new SubmarineAdapter(SubmarineItems.this, mSubmarines);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(SubmarineItems.this);
        mStorage = FirebaseStorage.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("submarine");
        mDBListener = mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mSubmarines.clear();
                for (DataSnapshot pizzaSnap : dataSnapshot.getChildren()) {
                    Submarine upload = pizzaSnap.getValue(Submarine.class);
                    upload.setKey(pizzaSnap.getKey());
                    mSubmarines.add(upload);
                }
                mAdapter.notifyDataSetChanged();
                mProgressBar.setVisibility(View.GONE);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(SubmarineItems.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressBar.setVisibility(View.INVISIBLE);
            }
        });
    }
    public void onItemClick(int position) {
        Submarine clickedTeacher=mSubmarines.get(position);
        String[] teacherData={
                clickedTeacher.getName(),
                clickedTeacher.getPrice()
        };

        //openDetailActivity(teacherData);
    }
    @Override
    public void onShowItemClick(int position) {
        Submarine clickedTeacher=mSubmarines.get(position);
        final String selectedKey = clickedTeacher.getKey();
        String[] teacherData={
                selectedKey,
                clickedTeacher.getName(),
                clickedTeacher.getPrice(),
                clickedTeacher.getUrl()
        };
        updateActivity(teacherData);
    }
    @Override
    public void onDeleteItemClick(int position) {
        Submarine selectedItem = mSubmarines.get(position);
        final String selectedKey = selectedItem.getKey();
        StorageReference imageRef = mStorage.getReferenceFromUrl(selectedItem.getUrl());
        imageRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                mDatabaseRef.child(selectedKey).removeValue();
                Toast.makeText(SubmarineItems.this, "Item deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }
    protected void onDestroy() {
        super.onDestroy();
        mDatabaseRef.removeEventListener(mDBListener);
    }
}