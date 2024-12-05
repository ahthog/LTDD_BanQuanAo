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

// Truy vấn dữ liệu sản phẩm
$sql = "SELECT * FROM SanPham";
$result = $conn->query($sql);

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
$conn->close();
?>
