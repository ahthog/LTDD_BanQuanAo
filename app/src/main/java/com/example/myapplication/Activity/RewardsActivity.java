package com.example.myapplication.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.DiscountTicketAdapter;
import com.example.myapplication.Model.DiscountTicket;
import com.example.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class RewardsActivity extends AppCompatActivity {
    private List<DiscountTicket> discountTicketList = new ArrayList<>();
    private RecyclerView recyclerView;
    private DiscountTicketAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewards);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DiscountTicketAdapter(discountTicketList);
        recyclerView.setAdapter(adapter);
        initializeUserPoints();
        displayUserPoints();

        Button redeemPointsButton = findViewById(R.id.redeemPointsButton);
        redeemPointsButton.setOnClickListener(v -> {
            int userPoints = loadUserPoints();
            int discountValue = calculateDiscountValue(userPoints); // Tính giá trị giảm giá dựa trên điểm

            if (userPoints >= 100) { // Kiểm tra đủ điểm quy đổi
                userPoints -= discountValue / 100; // Trừ điểm theo giá trị quy đổi
                saveUserPoints(userPoints); // Lưu lại điểm mới

                // Tạo vé giảm giá và thêm vào danh sách
                DiscountTicket ticket = new DiscountTicket("Vé giảm giá " + discountValue + " VND", discountValue);
                discountTicketList.add(ticket);
                adapter.notifyDataSetChanged(); // Cập nhật RecyclerView

                Toast.makeText(this, "Quy đổi thành công! Bạn đã được vé giảm giá " + discountValue + " VND.", Toast.LENGTH_SHORT).show();
                displayUserPoints(); // Cập nhật lại điểm trên giao diện
            } else {
                Toast.makeText(this, "Bạn không đủ điểm để quy đổi", Toast.LENGTH_SHORT).show();
            }
        });


    }

    // Hàm tính giá trị giảm giá dựa trên số điểm
    private int calculateDiscountValue(int userPoints) {
        int discountRate = 100; // Quy đổi 1 điểm = 100 VND
        return userPoints * discountRate;
    }

    private void displayUserPoints() {
        TextView pointsTextView = findViewById(R.id.pointsTextView);
        int userPoints = loadUserPoints();
        pointsTextView.setText("Điểm tích lũy của bạn: " + userPoints + " điểm");
    }

    private int loadUserPoints() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        return sharedPreferences.getInt("user_points", 0);
    }

    private void initializeUserPoints() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        // Kiểm tra xem người dùng đã có điểm hay chưa
        int userPoints = sharedPreferences.getInt("user_points", -1);  // Lấy điểm, nếu không có thì trả về -1
        if (userPoints == -1) {
            // Nếu người dùng chưa có điểm, đặt điểm mặc định (ví dụ: 70000 điểm)
            userPoints = 70000000;  // Giá trị điểm mặc định
            saveUserPoints(userPoints);  // Lưu điểm vào SharedPreferences
        }
    }

    private void saveUserPoints(int points) {
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("user_points", points);
        editor.apply();
    }


}
