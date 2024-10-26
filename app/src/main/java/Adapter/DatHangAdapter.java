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

import java.util.List;

import DatHang.DatHang;

public class DatHangAdapter extends RecyclerView.Adapter<DatHangAdapter.DatHangViewHolder> {

    private Context context;
    private List<DatHang> datHangList;

    // Constructor cho adapter
    public DatHangAdapter(Context context, List<DatHang> datHangList) {
        this.context = context;
        this.datHangList = datHangList;
    }

    @NonNull
    @Override
    public DatHangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_dathang, parent, false);
        return new DatHangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DatHangViewHolder holder, int position) {
        DatHang datHang = datHangList.get(position);

        // Gán giá trị cho các View
        holder.tenMatHang.setText(datHang.getTenMatHang());
        holder.sizeMatHang.setText("Size: " + datHang.getSize());
        holder.soLuong.setText("x" + datHang.getSoLuong());
        holder.tongTien.setText(String.valueOf(datHang.getTongTien()));
        holder.hinhAnh.setImageResource(datHang.getHinhAnh());
    }

    @Override
    public int getItemCount() {
        return datHangList.size();
    }

    // Lớp ViewHolder để giữ các view item
    public static class DatHangViewHolder extends RecyclerView.ViewHolder {

        TextView tenMatHang, sizeMatHang, soLuong, tongTien;
        ImageView hinhAnh;

        public DatHangViewHolder(@NonNull View itemView) {
            super(itemView);
            tenMatHang = itemView.findViewById(R.id.tenmathang1);
            sizeMatHang = itemView.findViewById(R.id.sizemathang1);
            soLuong = itemView.findViewById(R.id.number_display1);
            tongTien = itemView.findViewById(R.id.tong_tien1);
            hinhAnh = itemView.findViewById(R.id.imagemathang1);
        }
    }
}
