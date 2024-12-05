package com.example.myapplication.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.DiscountTicketAdapter;
import com.example.myapplication.Model.DiscountTicket;
import com.example.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HistoryPageActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DiscountTicketAdapter adapter;
    private List<DiscountTicket> discountTicketList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_page);

        recyclerView = findViewById(R.id.recyclerViewHistory);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Lấy danh sách vé giảm giá từ SharedPreferences
        discountTicketList = loadDiscountTickets();
        if (discountTicketList.isEmpty()) {
            Toast.makeText(this, "Chưa có vé giảm giá.", Toast.LENGTH_SHORT).show();
        }

        adapter = new DiscountTicketAdapter(discountTicketList);
        recyclerView.setAdapter(adapter);
    }

    private List<DiscountTicket> loadDiscountTickets() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String jsonTickets = sharedPreferences.getString("discount_tickets", "[]"); // Lấy chuỗi JSON của vé giảm giá

        List<DiscountTicket> discountTickets = new ArrayList<>();
        try {
            // Chuyển chuỗi JSON thành danh sách các vé
            JSONArray jsonArray = new JSONArray(jsonTickets);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String description = jsonObject.getString("description");
                int value = jsonObject.getInt("value");
                discountTickets.add(new DiscountTicket(description, value));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return discountTickets;
    }
}
