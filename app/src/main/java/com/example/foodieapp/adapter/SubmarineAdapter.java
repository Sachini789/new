package com.example.foodieapp.adapter;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.foodieapp.R;
import com.example.foodieapp.model.Pizza;
import com.example.foodieapp.model.Submarine;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class SubmarineAdapter extends RecyclerView.Adapter<SubmarineAdapter.RecyclerViewHolder>{
        private Context mContext;
        private ImageView btnDeleteSubmarine,submarineUpdate;
        private List<Submarine> submarines;
        private OnItemClickListener mListener;
        public SubmarineAdapter(Context context, List<Submarine> uploads) {
            mContext = context;
            submarines = uploads;
        }
        @Override
        public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(mContext).inflate(R.layout.submarine_model, parent, false);
            return new RecyclerViewHolder(v);
        }
        @Override
        public void onBindViewHolder(RecyclerViewHolder holder, int position) {
            Submarine submarine = submarines.get(position);

            holder.nameTextView.setText(submarine.getName());
            holder.price.setText(submarine.getPrice());


            Picasso.with(mContext)
                    .load(submarine.getUrl())
                    .placeholder(R.drawable.a1)
                    .fit()
                    .centerCrop()
                    .into(holder.submarineImageView);
        }
        @Override
        public int getItemCount() {
            return submarines.size();
        }
        public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
                View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
            public TextView nameTextView,price;
            public ImageView submarineImageView;
            public RecyclerViewHolder(View itemView) {
                super(itemView);
                nameTextView =itemView.findViewById ( R.id.submarineNameAdapter );
                price = itemView.findViewById(R.id.submarinePriceAdapter);
                submarineImageView = itemView.findViewById(R.id.submarineImageView);
                submarineUpdate = itemView.findViewById(R.id.submarineUpdate);
                btnDeleteSubmarine = itemView.findViewById(R.id.submarineDelete);
                itemView.setOnClickListener(this);
                itemView.setOnCreateContextMenuListener(this);

                btnDeleteSubmarine.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mListener != null) {
                            int position = getAdapterPosition();
                            if (position != RecyclerView.NO_POSITION) {
                                mListener.onDeleteItemClick(position);
                            }
                        }
                    }
                });
                submarineUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mListener != null) {
                            int position = getAdapterPosition();
                            if (position != RecyclerView.NO_POSITION) {
                                mListener.onShowItemClick(position);
                            }
                        }
                    }
                });
            }

            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        mListener.onItemClick(position);
                    }
                }
            }
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                menu.setHeaderTitle("Select Action");
                MenuItem showItem = menu.add( Menu.NONE, 1, 1, "Show");
                MenuItem deleteItem = menu.add(Menu.NONE, 2, 2, "Delete");
                showItem.setOnMenuItemClickListener(this);
                deleteItem.setOnMenuItemClickListener(this);
            }
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (mListener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        switch (item.getItemId()) {
                            case 1:
                                mListener.onShowItemClick(position);
                                return true;
                            case 2:
                                mListener.onDeleteItemClick(position);
                                return true;
                        }
                    }
                }
                return false;
            }
        }
        public interface OnItemClickListener {
            void onItemClick(int position);
            void onShowItemClick(int position);
            void onDeleteItemClick(int position);
        }
        public void setOnItemClickListener(OnItemClickListener listener) {
            mListener = listener;
        }

    }

