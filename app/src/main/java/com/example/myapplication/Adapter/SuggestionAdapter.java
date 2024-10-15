package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.myapplication.Model.Product;
import com.example.myapplication.R;

import java.util.List;


public class SuggestionAdapter extends RecyclerView.Adapter<SuggestionAdapter.SuggestionViewHolder> {
    private Context context;
    private List<Product> products;

    public SuggestionAdapter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public SuggestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_suggestion, parent, false);
        return new SuggestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SuggestionViewHolder holder, int position) {
        Product product = products.get(position);
        holder.productName.setText(product.getName());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class SuggestionViewHolder extends RecyclerView.ViewHolder {
        TextView productName;

        SuggestionViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.text_view_product_name);
        }
    }
}
