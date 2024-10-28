package GioHang;

import android.os.Parcel;
import android.os.Parcelable;

public class GioHang implements Parcelable {
    private String tenMathang;
    private String size;
    private int soLuong;
    private double giaGoc;
    private double giaGiam;
    private double thanhTien;
    private int imageResId;

    public GioHang(String tenMathang, String size, int soLuong, double giaGoc, double giaGiam, double thanhTien, int imageResId) {
        this.tenMathang = tenMathang;
        this.size = size;
        this.soLuong = soLuong;
        this.giaGoc = giaGoc;
        this.giaGiam = giaGiam;
        this.thanhTien = thanhTien;
        this.imageResId = imageResId;
    }

    // Getter và Setter cho soLuong
    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    // Các getter khác
    public String getTenMathang() {
        return tenMathang;
    }

    public String getSize() {
        return size;
    }

    public double getGiaGoc() {
        return giaGoc;
    }

    public double getGiaGiam() {
        return giaGiam;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public int getImageResId() {
        return imageResId;
    }

    // Phương thức Parcelable
    protected GioHang(Parcel in) {
        tenMathang = in.readString();
        size = in.readString();
        soLuong = in.readInt();
        giaGoc = in.readDouble();
        giaGiam = in.readDouble();
        thanhTien = in.readDouble();
        imageResId = in.readInt();
    }

    public static final Creator<GioHang> CREATOR = new Creator<GioHang>() {
        @Override
        public GioHang createFromParcel(Parcel in) {
            return new GioHang(in);
        }

        @Override
        public GioHang[] newArray(int size) {
            return new GioHang[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(tenMathang);
        dest.writeString(size);
        dest.writeInt(soLuong);
        dest.writeDouble(giaGoc);
        dest.writeDouble(giaGiam);
        dest.writeDouble(thanhTien);
        dest.writeInt(imageResId);
    }
}
