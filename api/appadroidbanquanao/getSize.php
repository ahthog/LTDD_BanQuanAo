<?php
include 'config.php';  // Kết nối cơ sở dữ liệu từ file config.php

header('Content-Type: application/json; charset=utf-8');

try {
    // Chuẩn bị câu lệnh SQL để lấy dữ liệu kích cỡ
    $stmt = $conn->prepare("SELECT MaKichCo AS id_size, TenKichCo AS Name_Size FROM KichCo");
    $stmt->execute(); // Thực thi câu lệnh SQL
    $sizes = $stmt->fetchAll(PDO::FETCH_ASSOC); // Lấy tất cả dữ liệu kích cỡ

    // Trả về kết quả dưới dạng JSON
    echo json_encode($sizes);
} catch (PDOException $e) {
    // Nếu có lỗi xảy ra, trả về thông báo lỗi dưới dạng JSON
    echo json_encode(['status' => 'error', 'message' => $e->getMessage()]);
}
?>
