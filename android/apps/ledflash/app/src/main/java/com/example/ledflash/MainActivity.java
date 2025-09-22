package com.example.ledflash;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private boolean isOn = false;
    private View rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rootView = findViewById(android.R.id.content);

        Button btn = findViewById(R.id.flashButton);
        btn.setOnClickListener(v -> {
            isOn = !isOn;
            rootView.setBackgroundColor(isOn ? Color.YELLOW : Color.BLACK);
            btn.setText(isOn ? "Turn OFF" : "Turn ON");
        });
    }
}