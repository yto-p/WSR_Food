package com.app.resaless.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.resaless.R;
import com.app.resaless.item.FoodItem;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {

    public List<FoodItem> foodItems = new ArrayList<>();
    LayoutInflater inflater;

    public FoodAdapter(Context context){
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.food_item, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull FoodAdapter.ViewHolder holder, int position) {
        FoodItem foodItem = foodItems.get(position);
        Glide.with(holder.avatar)
                .load(foodItem.avatar)
                .into(holder.avatar);
        holder.title.setText(foodItem.title);
        holder.price.setText(foodItem.price + "₽");

    }

    @Override
    public int getItemCount() {
        return foodItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView avatar;
        TextView title, price;

        ViewHolder(View view) {
            super(view);
            avatar = view.findViewById(R.id.avatar);
            title = view.findViewById(R.id.title);
            price = view.findViewById(R.id.price);
        }
    }
}
