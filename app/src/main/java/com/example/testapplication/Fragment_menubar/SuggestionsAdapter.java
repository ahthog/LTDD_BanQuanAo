package com.example.testapplication.Fragment_menubar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.testapplication.R;

public class SuggestionsAdapter extends RecyclerView.Adapter<SuggestionsAdapter.ViewHolder> {

    private String[] suggestions;

    // Constructor for the adapter
    public SuggestionsAdapter(String[] suggestions) {
        this.suggestions = suggestions;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate layout for individual items in RecyclerView
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Bind suggestion text to the view
        holder.suggestionText.setText(suggestions[position]);
    }

    @Override
    public int getItemCount() {
        // Return the number of items in the list
        return suggestions.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView suggestionText;

        public ViewHolder(View itemView) {
            super(itemView);
            suggestionText = itemView.findViewById(R.id.suggestion_text);
        }
    }
}
