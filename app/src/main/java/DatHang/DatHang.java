package DatHang;

public class DatHang {
    private String tenMatHang;  // Tên sản phẩm
    private String size;        // Size của sản phẩm
    private int soLuong;        // Số lượng sản phẩm
    private double tongTien;    // Tổng tiền
    private int hinhAnh;        // ID của ảnh trong drawable

    public DatHang(String tenMatHang, String size, int soLuong, double tongTien, int hinhAnh) {
        this.tenMatHang = tenMatHang;
        this.size = size;
        this.soLuong = soLuong;
        this.tongTien = tongTien;
        this.hinhAnh = hinhAnh;
    }

    // Getter và Setter cho các thuộc tính
    public String getTenMatHang() { return tenMatHang; }
    public void setTenMatHang(String tenMatHang) { this.tenMatHang = tenMatHang; }

    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }

    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }

    public double getTongTien() { return tongTien; }
    public void setTongTien(double tongTien) { this.tongTien = tongTien; }

    public int getHinhAnh() { return hinhAnh; }
    public void setHinhAnh(int hinhAnh) { this.hinhAnh = hinhAnh; }
}
