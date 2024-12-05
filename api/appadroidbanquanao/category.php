<?php
include 'config.php';
header('Content-Type: application/json; charset=utf-8');

try {
    $stmt = $conn->prepare("SELECT MaDanhMuc AS  resourceId, TenDanhMuc AS name, HinhAnh AS imageUrl FROM danhmuc");
    $stmt->execute();
    $products = $stmt->fetchAll(PDO::FETCH_ASSOC);

    echo json_encode($products);
} catch (PDOException $e) {
    echo json_encode(['status' => 'error', 'message' => $e->getMessage()]);
}
?>
