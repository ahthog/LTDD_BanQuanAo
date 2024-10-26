package DatHang;

public class ThanhCong {
    private int hinhAnh;  // ID của ảnh trong drawable
    private String tenMatHang; // Tên sản phẩm

    // Constructor
    public ThanhCong(int hinhAnh, String tenMatHang) {
        this.hinhAnh = hinhAnh;
        this.tenMatHang = tenMatHang;
    }

    // Getter và Setter cho các thuộc tính
    public int getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(int hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getTenMatHang() {
        return tenMatHang;
    }

    public void setTenMatHang(String tenMatHang) {
        this.tenMatHang = tenMatHang;
    }
}
