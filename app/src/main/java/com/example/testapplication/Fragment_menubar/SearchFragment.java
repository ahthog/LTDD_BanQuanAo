package com.example.testapplication.Fragment_menubar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapplication.R;

public class SearchFragment extends Fragment {

    private AutoCompleteTextView searchAutoComplete;
    private RecyclerView recyclerViewSuggestions;
    private SuggestionsAdapter suggestionsAdapter;

    // Dữ liệu gợi ý mẫu
    private String[] suggestions = new String[]{
            "Áo", "Giày", "Chân vay", "Mũ"
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        // Khởi tạo AutoCompleteTextView
        searchAutoComplete = view.findViewById(R.id.search_auto_complete);
        recyclerViewSuggestions = view.findViewById(R.id.recycler_view_suggestions);

        // Tạo và thiết lập adapter cho AutoCompleteTextView
        ArrayAdapter<String> autoCompleteAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, suggestions);
        searchAutoComplete.setAdapter(autoCompleteAdapter);

        // Thiết lập sự kiện cho AutoCompleteTextView
        searchAutoComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parentView, View view, int position, long id) {
                String selectedSuggestion = (String) parentView.getItemAtPosition(position);
                // Hiển thị gợi ý đã chọn
                Toast.makeText(getContext(), "Bạn đã chọn: " + selectedSuggestion, Toast.LENGTH_SHORT).show();
            }
        });

        // Thiết lập RecyclerView cho danh sách gợi ý
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewSuggestions.setLayoutManager(layoutManager);

        suggestionsAdapter = new SuggestionsAdapter(suggestions);
        recyclerViewSuggestions.setAdapter(suggestionsAdapter);

        return view;
    }
}
