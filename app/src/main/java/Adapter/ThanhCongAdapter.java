package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapplication.R;

import DatHang.ThanhCong;
import java.util.List;

public class ThanhCongAdapter extends RecyclerView.Adapter<ThanhCongAdapter.ViewHolder> {
    private List<ThanhCong> danhSachThanhCong; // Danh sách dữ liệu
    private Context context;

    // Constructor
    public ThanhCongAdapter(List<ThanhCong> danhSachThanhCong, Context context) {
        this.danhSachThanhCong = danhSachThanhCong;
        this.context = context;
    }

    // Tạo ViewHolder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_thanhcong, parent, false);
        return new ViewHolder(view);
    }

    // Ràng buộc dữ liệu vào ViewHolder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ThanhCong item = danhSachThanhCong.get(position);
        holder.itemText.setText(item.getTenMatHang());
        holder.itemImage.setImageResource(item.getHinhAnh());
    }

    // Trả về số lượng item trong danh sách
    @Override
    public int getItemCount() {
        return danhSachThanhCong.size();
    }

    // Lớp ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImage;
        TextView itemText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.item_image);
            itemText = itemView.findViewById(R.id.item_text);
        }
    }
}
