package com.example.testapplication;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private int progressStatus = 0; // Giá trị ban đầu của tiến trình
    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Đảm bảo bạn đã set layout cho activity

        // Khởi tạo ProgressBar
        progressBar = findViewById(R.id.progressBarDeterminate);

        // Tạo một thread mới để cập nhật tiến trình
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progressStatus < 100) { // Giả sử tiến trình chạy đến 100%
                    progressStatus += 1; // Tăng giá trị tiến trình
                    // Sử dụng handler để cập nhật UI
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progressStatus); // Cập nhật giá trị ProgressBar
                        }
                    });
                    try {
                        // Giả lập thời gian tải
                        Thread.sleep(100); // Dừng 100ms giữa mỗi lần cập nhật
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Intent intent = new Intent(MainActivity.this, LoginRegisterActivity.class);
                startActivity(intent);
                finish();
            }
        }).start();
    }
}