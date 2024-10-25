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

import com.example.testapplication.R;

import java.util.List;

import GioHang.GioHang;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.GioHangViewHolder> {

    private Context context;
    private List<GioHang> gioHangList;

    public GioHangAdapter(Context context, List<GioHang> gioHangList) {
        this.context = context;
        this.gioHangList = gioHangList;
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
        holder.giaGoc.setText(String.valueOf(gioHang.getGiaGoc()));
        holder.giaGiam.setText(String.valueOf(gioHang.getGiaGiam()));
        holder.tongTien.setText(String.valueOf(gioHang.getTongTien()));
        holder.imagemathang.setImageResource(gioHang.getImageResId()); // Đặt hình ảnh

        // Thiết lập Adapter cho Spinner
        String[] sizeOptions = {"S", "M", "L", "XL"};
        ArrayAdapter<String> sizeAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, sizeOptions);
        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.size.setAdapter(sizeAdapter);

        // Chọn mục tương ứng với kích cỡ trong giỏ hàng
        int spinnerPosition = sizeAdapter.getPosition(gioHang.getSize());
        holder.size.setSelection(spinnerPosition);

        // Xử lý sự kiện cho các nút tăng/giảm số lượng
        holder.buttonTang.setOnClickListener(v -> {
            int newQuantity = gioHang.getSoLuong() + 1;
            gioHang.setSoLuong(newQuantity);
            holder.soLuong.setText(String.valueOf(newQuantity));
            holder.tongTien.setText(String.valueOf((gioHang.getGiaGoc() - gioHang.getGiaGiam()) * newQuantity));
        });

        holder.buttonGiam.setOnClickListener(v -> {
            int newQuantity = gioHang.getSoLuong() - 1;
            if (newQuantity > 0) {
                gioHang.setSoLuong(newQuantity);
                holder.soLuong.setText(String.valueOf(newQuantity));
                holder.tongTien.setText(String.valueOf((gioHang.getGiaGoc() - gioHang.getGiaGiam()) * newQuantity));
            }
        });
    }

    @Override
    public int getItemCount() {
        return gioHangList.size();
    }

    public static class GioHangViewHolder extends RecyclerView.ViewHolder {

        TextView tenMathang, soLuong, giaGoc, giaGiam, tongTien;
        ImageView imagemathang;
        Button buttonTang, buttonGiam;
        Spinner size;

        public GioHangViewHolder(@NonNull View itemView) {
            super(itemView);
            tenMathang = itemView.findViewById(R.id.tenmathang1);
            size = itemView.findViewById(R.id.size1);
            soLuong = itemView.findViewById(R.id.number_display1);
            giaGoc = itemView.findViewById(R.id.tien_goc1);
            giaGiam = itemView.findViewById(R.id.tien_giam1);
            tongTien = itemView.findViewById(R.id.tong_tien1);
            imagemathang = itemView.findViewById(R.id.imagemathang1);
            buttonTang = itemView.findViewById(R.id.button_tang1);
            buttonGiam = itemView.findViewById(R.id.button_giam1);
        }
    }
}
