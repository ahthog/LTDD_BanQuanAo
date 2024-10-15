package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.Category;
import com.example.myapplication.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    private List<Category> mListCategory;
    private Context mContext;
    private OnCategoryClickListener listener;

    public interface OnCategoryClickListener {
        void onCategoryClick(Category category);
    }

    public CategoryAdapter(Context context, List<Category> mListCategory, OnCategoryClickListener listener) {
        this.mListCategory = mListCategory;
        this.mContext = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = mListCategory.get(position);
        if (category == null) {
            return;
        }
        holder.imgCategory.setImageResource(category.getResourceId());
        holder.tvName.setText(category.getName());
        holder.layoutCategory.setOnClickListener(view -> listener.onCategoryClick(category)); // Gọi listener
    }

    @Override
    public int getItemCount() {
        return mListCategory != null ? mListCategory.size() : 0;
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout layoutCategory;
        private ImageView imgCategory;
        private TextView tvName;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCategory = itemView.findViewById(R.id.imageItemCategory);
            tvName = itemView.findViewById(R.id.tvItemCategory);
            layoutCategory = itemView.findViewById(R.id.layout_category); // Đảm bảo rằng bạn đã khai báo layoutCategory
        }
    }
}
