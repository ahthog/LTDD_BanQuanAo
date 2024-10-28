package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import GioHang.GioHang;
import com.example.testapplication.R;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.GioHangViewHolder> {

    private Context context;
    private List<GioHang> gioHangList;
    private OnQuantityChangeListener quantityChangeListener;

    public GioHangAdapter(Context context, List<GioHang> gioHangList, OnQuantityChangeListener listener) {
        this.context = context;
        this.gioHangList = gioHangList;
        this.quantityChangeListener = listener;
    }

    @NonNull
    @Override
    public GioHangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_giohang, parent, false);
        return new GioHangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GioHangViewHolder holder, int position) {
        GioHang gioHang = gioHangList.get(position);

        // Thiết lập các giá trị cho các thuộc tính
        holder.tenMathang.setText(gioHang.getTenMathang());
        holder.soLuong.setText(String.valueOf(gioHang.getSoLuong()));

        // Cập nhật giá gốc, giá giảm và thành tiền
        holder.giaGoc.setText(String.valueOf(gioHang.getGiaGoc())); // Giá gốc
        holder.giaGiam.setText(String.valueOf(gioHang.getGiaGiam())); // Giá giảm

        // Tính thành tiền dựa trên số lượng
        double tongTien = gioHang.getGiaGoc() * gioHang.getSoLuong() - gioHang.getGiaGiam() * gioHang.getSoLuong();
        holder.tongTien.setText(String.valueOf(tongTien)); // Thành tiền
        holder.imagemathang.setImageResource(gioHang.getImageResId());

        // Thiết lập Adapter cho Spinner
        String[] sizeOptions = {"S", "M", "L", "XL"};
        ArrayAdapter<String> sizeAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, sizeOptions);
        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.size.setAdapter(sizeAdapter);

        // Chọn mục tương ứng với kích cỡ trong giỏ hàng
        int spinnerPosition = sizeAdapter.getPosition(gioHang.getSize());
        holder.size.setSelection(spinnerPosition);

        // Thêm sự kiện click cho nút xóa
        holder.delete.setOnClickListener(v -> {
            removeItem(position);
            quantityChangeListener.onQuantityChanged(); // Cập nhật tổng số tiền khi xóa sản phẩm
        });

        // Thêm sự kiện click cho nút giảm số lượng
        holder.buttonGiam.setOnClickListener(v -> {
            int currentQuantity = gioHang.getSoLuong();
            if (currentQuantity > 1) {
                currentQuantity--;
                gioHang.setSoLuong(currentQuantity);
                holder.soLuong.setText(String.valueOf(currentQuantity));
                // Cập nhật lại giá trị thành tiền
                double newTongTien = gioHang.getGiaGoc() * currentQuantity - gioHang.getGiaGiam() * currentQuantity;
                holder.tongTien.setText(String.valueOf(newTongTien));
                quantityChangeListener.onQuantityChanged(); // Cập nhật tổng số tiền khi giảm số lượng
            }
        });

        // Thêm sự kiện click cho nút tăng số lượng
        holder.buttonTang.setOnClickListener(v -> {
            int currentQuantity = gioHang.getSoLuong();
            currentQuantity++;
            gioHang.setSoLuong(currentQuantity);
            holder.soLuong.setText(String.valueOf(currentQuantity));
            // Cập nhật lại giá trị thành tiền
            double newTongTien = gioHang.getGiaGoc() * currentQuantity - gioHang.getGiaGiam() * currentQuantity;
            holder.tongTien.setText(String.valueOf(newTongTien));
            quantityChangeListener.onQuantityChanged(); // Cập nhật tổng số tiền khi tăng số lượng
        });
    }


    public void removeItem(int position) {
        gioHangList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, gioHangList.size()); // Cập nhật lại vị trí các item
    }

    @Override
    public int getItemCount() {
        return gioHangList.size();
    }

    public static class GioHangViewHolder extends RecyclerView.ViewHolder {
        TextView tenMathang, soLuong, giaGoc, giaGiam, tongTien, delete; // Thêm delete vào danh sách
        ImageView imagemathang;
        Spinner size;
        Button buttonGiam, buttonTang; // Nút giảm và tăng

        public GioHangViewHolder(@NonNull View itemView) {
            super(itemView);
            tenMathang = itemView.findViewById(R.id.tenmathang1);
            size = itemView.findViewById(R.id.size1);
            soLuong = itemView.findViewById(R.id.number_display1);
            giaGoc = itemView.findViewById(R.id.tien_goc1);
            giaGiam = itemView.findViewById(R.id.tien_giam1);
            tongTien = itemView.findViewById(R.id.tong_tien1);
            imagemathang = itemView.findViewById(R.id.imagemathang1);
            delete = itemView.findViewById(R.id.delete1); // Khởi tạo delete
            buttonGiam = itemView.findViewById(R.id.button_giam1); // Khởi tạo nút giảm
            buttonTang = itemView.findViewById(R.id.button_tang1); // Khởi tạo nút tăng
        }
    }

    // Interface để thông báo thay đổi số lượng
    public interface OnQuantityChangeListener {
        void onQuantityChanged();
    }
}
