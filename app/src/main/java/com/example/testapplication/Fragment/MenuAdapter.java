package com.example.testapplication.Fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.testapplication.R;
import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {

    private List<MenuItem> menuItemList;
    private Context context;

    // Constructor
    public MenuAdapter(Context context, List<MenuItem> menuItemList) {
        this.context = context;
        this.menuItemList = menuItemList;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate layout item
        View view = LayoutInflater.from(context).inflate(R.layout.item_menu, parent, false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        // Get current item
        MenuItem menuItem = menuItemList.get(position);

        // Bind data
        holder.menuItemText.setText(menuItem.getText());
        holder.menuItemImage.setImageResource(menuItem.getImageResId());  // Assuming you are using resource id for images
    }

    @Override
    public int getItemCount() {
        return menuItemList.size();
    }

    public static class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView menuItemText;
        ImageView menuItemImage;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            menuItemText = itemView.findViewById(R.id.menu_item_text);
            menuItemImage = itemView.findViewById(R.id.menu_item_image);
        }
    }
}
