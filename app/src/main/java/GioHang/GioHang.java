package GioHang;

public class GioHang {
    private String tenMathang;      // Tên sản phẩm
    private String size;            // Kích cỡ sản phẩm
    private int soLuong;            // Số lượng sản phẩm
    private double giaGoc;          // Giá gốc của sản phẩm
    private double giaGiam;         // Giá giảm (nếu có)
    private double tongTien;        // Tổng tiền sau khi giảm
    private int imageResId;         // Tham số cho hình ảnh

    // Constructor
    public GioHang(String tenMathang, String size, int soLuong, double giaGoc, double giaGiam, double tongTien, int imageResId) {
        this.tenMathang = tenMathang;
        this.size = size;
        this.soLuong = soLuong;
        this.giaGoc = giaGoc;
        this.giaGiam = giaGiam;
        this.tongTien = tongTien;
        this.imageResId = imageResId; // Gán giá trị cho imageResId
    }

    public String getTenMathang() {
        return tenMathang;
    }

    public void setTenMathang(String tenMathang) {
        this.tenMathang = tenMathang;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getGiaGoc() {
        return giaGoc;
    }

    public void setGiaGoc(double giaGoc) {
        this.giaGoc = giaGoc;
    }

    public double getGiaGiam() {
        return giaGiam;
    }

    public void setGiaGiam(double giaGiam) {
        this.giaGiam = giaGiam;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }
}

