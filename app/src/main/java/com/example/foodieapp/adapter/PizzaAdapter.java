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
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class PizzaAdapter extends RecyclerView.Adapter<PizzaAdapter.RecyclerViewHolder>{
        private Context mContext;
        private ImageView btnDeletePizza,pizzaUpdate;
        private List<Pizza> pizza;
        private OnItemClickListener mListener;
        public PizzaAdapter(Context context, List<Pizza> uploads) {
            mContext = context;
            pizza = uploads;
        }
        @Override
        public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(mContext).inflate(R.layout.pizza_model, parent, false);
            return new RecyclerViewHolder(v);
        }
        @Override
        public void onBindViewHolder(RecyclerViewHolder holder, int position) {
            Pizza pizz = pizza.get(position);

            holder.nameTextView.setText(pizz.getName());
            holder.descriptionTextView.setText(pizz.getPrice());
            holder.dateTextView.setText(getDateToday());

            Picasso.with(mContext)
                    .load(pizz.getUrl())
                    .placeholder(R.drawable.a1)
                    .fit()
                    .centerCrop()
                    .into(holder.pizzaImageView);
        }
        @Override
        public int getItemCount() {
            return pizza.size();
        }
        public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
                View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
            public TextView nameTextView,descriptionTextView,dateTextView;
            public ImageView pizzaImageView;
            public RecyclerViewHolder(View itemView) {
                super(itemView);
                nameTextView =itemView.findViewById ( R.id.nameTextView );
                descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
                dateTextView = itemView.findViewById(R.id.dateTextView);
                pizzaImageView = itemView.findViewById(R.id.pizzaImageView);
                pizzaUpdate = itemView.findViewById(R.id.pizzaUpdate);
                btnDeletePizza = itemView.findViewById(R.id.pizzaDelete);
                itemView.setOnClickListener(this);
                itemView.setOnCreateContextMenuListener(this);

                btnDeletePizza.setOnClickListener(new View.OnClickListener() {
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
                pizzaUpdate.setOnClickListener(new View.OnClickListener() {
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
        private String getDateToday(){
            DateFormat dateFormat=new SimpleDateFormat("yyyy/MM/dd");
            Date date=new Date();
            String today= dateFormat.format(date);
            return today;
        }
    }

