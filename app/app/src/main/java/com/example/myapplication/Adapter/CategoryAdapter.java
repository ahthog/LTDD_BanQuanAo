package com.example.myapplication.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Fragments.CategoryDetailFragment;
import com.example.myapplication.Model.Category;
import com.example.myapplication.R;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    private List<Category> mListCategory;
    private OnCategoryClickListener listener;
    private final Context context;
    private final int viewType;

    public interface OnCategoryClickListener {
        void onCategoryClick(Category category);
    }

    public CategoryAdapter(Context context, List<Category> mListCategory, int viewType, OnCategoryClickListener listener) {
        this.mListCategory = mListCategory;
        this.context = context;
        this.viewType = viewType;
        this.listener = listener;
    }
    @Override
    public int getItemViewType(int position) {
        return viewType; // Trả về viewType, có thể phân biệt loại hiển thị
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

        holder.tvName.setText(category.getName());
        holder.layoutCategory.setOnClickListener(view -> {
            // Khi click vào danh mục, gọi listener
            listener.onCategoryClick(category);

            // Chuyển sang CategoryDetailFragment và truyền categoryId
            FragmentTransaction transaction = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
            CategoryDetailFragment categoryDetailFragment = new CategoryDetailFragment();

            // Truyền dữ liệu vào Bundle
            Bundle bundle = new Bundle();
            bundle.putInt("categoryId", category.getResourceId()); // Truyền categoryId vào Bundle
            categoryDetailFragment.setArguments(bundle);

            // Thực hiện chuyển fragment
            transaction.replace(R.id.fragment_container, categoryDetailFragment); // fragment_container là nơi chứa fragment
            transaction.addToBackStack(null); // Để có thể quay lại fragment trước
            transaction.commit();
        });

        // Dùng Picasso để tải hình ảnh từ URL lấy từ cơ sở dữ liệu
        String hinhAnhUrl = "http://192.168.21.155:8081/appadroidbanquanao/images/" + category.getImageUrl();  // Đây là URL của hình ảnh

        // Log URL để kiểm tra
        Log.d("ImageURL", hinhAnhUrl);

        // Tải lại hình ảnh từ URL và đảm bảo tải từ mạng, không dùng cache
        Picasso.get()
                .load(hinhAnhUrl)
                .networkPolicy(NetworkPolicy.NO_CACHE)  // Đảm bảo tải từ mạng (khong dung lai bo nho cache cu)
                .into(holder.imgCategory);  // Gán hình ảnh vào ImageView


    }
// tra ve so luong category
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
            layoutCategory = itemView.findViewById(R.id.layout_category);
        }
    }
}
