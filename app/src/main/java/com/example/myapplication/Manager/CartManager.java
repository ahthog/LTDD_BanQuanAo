package com.example.myapplication.Manager;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.myapplication.Model.CartItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static final String PREFS_NAME = "cart_prefs";
    private static final String CART_KEY = "cart_items";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Gson gson;

    public CartManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    public void addToCart(CartItem cartItem) {
        List<CartItem> cartItems = getCartItems();
        boolean itemExists = false;

        for (CartItem item : cartItems) {
            // Kiểm tra xem mặt hàng đã có trong giỏ chưa
            if (item.getProductName().equals(cartItem.getProductName()) && item.getSize().equals(cartItem.getSize())) {
                item.increaseQuantity(); // Tăng số lượng nếu đã có trong giỏ
                itemExists = true;
                break;
            }
        }

        // Nếu mặt hàng chưa có, thêm vào giỏ hàng
        if (!itemExists) {
            cartItems.add(cartItem);
        }

        saveCartItems(cartItems);
    }

    public void decreaseQuantity(CartItem cartItem) {
        List<CartItem> cartItems = getCartItems();

        // Tìm sản phẩm trong giỏ hàng và giảm số lượng nếu có
        for (CartItem item : cartItems) {
            if (item.getProductName().equals(cartItem.getProductName()) && item.getSize().equals(cartItem.getSize())) {
                item.decreaseQuantity();  // Giảm số lượng
                break;
            }
        }

        // Lưu lại giỏ hàng sau khi thay đổi
        saveCartItems(cartItems);
    }

    public void increaseQuantity(CartItem cartItem) {
        List<CartItem> cartItems = getCartItems();

        // Tìm sản phẩm trong giỏ hàng và tăng số lượng nếu có
        for (CartItem item : cartItems) {
            if (item.getProductName().equals(cartItem.getProductName()) && item.getSize().equals(cartItem.getSize())) {
                item.increaseQuantity();  // Tăng số lượng
                break;
            }
        }

        // Lưu lại giỏ hàng sau khi thay đổi
        saveCartItems(cartItems);
    }

    // Phương thức này đếm tổng số mặt hàng trong giỏ hàng mà không tính số lượng của từng mặt hàng
    public int getTotalItemCount() {
        List<CartItem> cartItems = getCartItems();
        return cartItems.size();  // Trả về số lượng các mặt hàng trong giỏ
    }


    // Lấy danh sách sản phẩm trong giỏ hàng
    public List<CartItem> getCartItems() {
        String json = sharedPreferences.getString(CART_KEY, null);
        Type type = new TypeToken<ArrayList<CartItem>>() {}.getType();
        return json == null ? new ArrayList<>() : gson.fromJson(json, type);
    }

    public void removeFromCart(CartItem cartItemToRemove) {
        List<CartItem> cartItems = getCartItems();

        // Duyệt qua danh sách giỏ hàng để tìm và xóa sản phẩm cần xóa
        for (int i = 0; i < cartItems.size(); i++) {
            CartItem item = cartItems.get(i);
            if (item.getProductName().equals(cartItemToRemove.getProductName()) && item.getSize().equals(cartItemToRemove.getSize())) {
                cartItems.remove(i);  // Xóa sản phẩm khỏi giỏ hàng
                break;
            }
        }

        // Lưu lại giỏ hàng sau khi thay đổi
        saveCartItems(cartItems);
    }



    // Xóa tất cả sản phẩm trong giỏ hàng
    public void clearCart() {
        sharedPreferences.edit().remove(CART_KEY).apply();
    }

    // Tính tổng tiền của giỏ hàng
    public double getTotalPrice() {
        double total = 0.0;
        List<CartItem> cartItems = getCartItems();
        for (CartItem item : cartItems) {
            total += item.getTotalPrice(); // Giả sử CartItem có phương thức getTotalPrice()
        }
        return total;
    }

    // Lưu giỏ hàng vào SharedPreferences
    private void saveCartItems(List<CartItem> cartItems) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String json = gson.toJson(cartItems);  // Chuyển đổi danh sách giỏ hàng thành chuỗi JSON
        editor.putString(CART_KEY, json);  // Lưu giỏ hàng dưới dạng JSON vào SharedPreferences
        editor.apply();  // Đảm bảo lưu các thay đổi
    }
}
