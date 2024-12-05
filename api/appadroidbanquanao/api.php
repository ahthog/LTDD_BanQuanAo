<?php
include 'config.php';
header('Content-Type: application/json; charset=utf-8');

try {
    // Kiểm tra nếu có tham số 'categoryId' trong request
    if (isset($_GET['categoryId'])) {
        $categoryId = $_GET['categoryId'];
        $stmt = $conn->prepare("SELECT HinhAnh AS imageUrl, TenSanPham AS name, Mau AS color, MoTa AS description, GiaBan AS price, DanhGia AS rating 
                                FROM SanPham 
                                WHERE MaDanhMuc = :categoryId");
        $stmt->bindParam(':categoryId', $categoryId, PDO::PARAM_INT);
    } else {
        $stmt = $conn->prepare("SELECT HinhAnh AS imageUrl, TenSanPham AS name, Mau AS color, MoTa AS description, GiaBan AS price, DanhGia AS rating 
                                FROM SanPham");
    }

    $stmt->execute();
    $products = $stmt->fetchAll(PDO::FETCH_ASSOC);

    echo json_encode($products);
} catch (PDOException $e) {
    echo json_encode(['status' => 'error', 'message' => $e->getMessage()]);
}
?>
