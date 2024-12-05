<?php
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");

// Kết nối cơ sở dữ liệu
$servername = "127.0.0.1";
$username = "root";  // Tên người dùng MySQL mặc định
$password = "";  // Mật khẩu mặc định cho MySQL trong XAMPP
$dbname = "appadroidbanquanao";  // Tên cơ sở dữ liệu

// Tạo kết nối
$conn = new mysqli($servername, $username, $password, $dbname);

// Kiểm tra kết nối
if ($conn->connect_error) {
    die(json_encode(["error" => "Failed to connect to database"]));
}

// Giả sử bạn muốn lấy danh sách sản phẩm yêu thích của người dùng với mã người dùng = 1
$userId = 1;  // Thay đổi tùy theo nhu cầu

// Truy vấn dữ liệu từ bảng SanPham và YeuThich
$sql = "SELECT p.MaSanPham , p.DanhGia as rating, p.MoTa as description, p.TenSanPham as name , p.HinhAnh as imageUrl, p.GiaBan as price, p.Mau as color
        FROM SanPham p
        JOIN YeuThich y ON p.MaSanPham = y.MaSanPham
        WHERE y.MaNguoiDung = ?";

// Chuẩn bị câu truy vấn
$stmt = $conn->prepare($sql);

// Kiểm tra xem câu lệnh đã chuẩn bị thành công chưa
if (!$stmt) {
    die(json_encode(["error" => "Failed to prepare SQL query"]));
}

// Liên kết tham số với giá trị người dùng
$stmt->bind_param("i", $userId);

// Thực thi câu truy vấn
$stmt->execute();

// Lấy kết quả
$result = $stmt->get_result();

// Khởi tạo mảng để chứa dữ liệu
$data = [];
if ($result->num_rows > 0) {
    while ($row = $result->fetch_assoc()) {
        $data[] = $row;
    }
}

// Trả về dữ liệu dưới dạng JSON
echo json_encode($data);

// Đóng kết nối
$stmt->close();
$conn->close();
?>
