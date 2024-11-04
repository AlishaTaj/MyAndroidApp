package com.example.mcaeventbrochure;  // Ensure the correct package declaration

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BrochureActivity extends AppCompatActivity {
    private TextView tvEventName, tvEventMode, tvEventDate, tvEventRating;
    private ImageView eventImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brochure);

        // Initialize TextViews and ImageView
        tvEventName = findViewById(R.id.tvEventName);
        tvEventMode = findViewById(R.id.tvEventMode);
        tvEventDate = findViewById(R.id.tvEventDate);
        tvEventRating = findViewById(R.id.tvEventRating);
        eventImageView = findViewById(R.id.eventImageView);

        // Get values from the Intent
        Intent intent = getIntent();
        String eventName = intent.getStringExtra("eventName");
        boolean eventMode = intent.getBooleanExtra("eventMode", false);
        String eventDate = intent.getStringExtra("eventDate");
        float eventRating = intent.getFloatExtra("eventRating", 0);

        // Display the received values
        tvEventName.setText("Event Name: " + eventName);
        tvEventMode.setText("Event Mode: " + (eventMode ? "Online" : "Offline"));
        tvEventDate.setText("Event Date: " + eventDate);
        tvEventRating.setText("Event Rating: " + eventRating);

        // You can dynamically change the image if required
        eventImageView.setImageResource(R.drawable.brochure);  // Set the desired image here
    }
}
