<?php
// config.php
$host = 'localhost';
$db = 'appadroidbanquanao'; // Tên database của bạn
$user = 'root';       // Tên người dùng MySQL
$pass = '';           // Mật khẩu MySQL (mặc định trống)

try {
    $conn = new PDO("mysql:host=$host;dbname=$db;charset=utf8mb4", $user, $pass);
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
} catch (PDOException $e) {
    die("Lỗi kết nối: " . $e->getMessage());
}
?>
