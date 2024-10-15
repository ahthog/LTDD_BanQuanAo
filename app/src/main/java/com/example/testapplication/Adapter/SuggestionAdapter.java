package com.example.testapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapplication.Fragment_menubar.Product;
import com.example.testapplication.R;

import java.util.List;

public class SuggestionAdapter extends RecyclerView.Adapter<SuggestionAdapter.ViewHolder> {
    private final Context context;
    private final List<Product> suggestions;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Product product);
    }

    public SuggestionAdapter(Context context, List<Product> suggestions, OnItemClickListener listener) {
        this.context = context;
        this.suggestions = suggestions;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_suggestion, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = suggestions.get(position);
        holder.suggestionName.setText(product.getName());
        holder.itemView.setOnClickListener(v -> listener.onItemClick(product));
    }

    @Override
    public int getItemCount() {
        return suggestions.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView suggestionName;

        ViewHolder(View itemView) {
            super(itemView);
            suggestionName = itemView.findViewById(R.id.suggestion_name);
        }
    }
}
