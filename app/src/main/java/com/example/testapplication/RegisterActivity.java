package com.example.testapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.layout_register);


        ImageView back = findViewById(R.id.imageback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginRegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}


