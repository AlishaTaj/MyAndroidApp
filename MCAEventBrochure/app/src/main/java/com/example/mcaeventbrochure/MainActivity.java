package com.example.mcaeventbrochure;  // Ensure the correct package declaration

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private EditText editEventName;
    private Switch switchEventMode;
    private TextView tvSelectedDate;
    private RatingBar ratingBar;
    private ImageSwitcher imageSwitcher;
    private int[] images = {R.drawable.brochure, R.drawable.department, R.drawable.logo};  // Your drawable images
    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        editEventName = findViewById(R.id.editEventName);
        switchEventMode = findViewById(R.id.switchEventMode);
        tvSelectedDate = findViewById(R.id.tvSelectedDate);
        ratingBar = findViewById(R.id.ratingBar);
        imageSwitcher = findViewById(R.id.imageSwitcher);

        // Setup ImageSwitcher
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(MainActivity.this);
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                return imageView;
            }
        });
        imageSwitcher.setImageResource(images[currentIndex]);

        // Handle image switcher button click
        Button btnSwitchImage = findViewById(R.id.btnSwitchImage);
        btnSwitchImage.setOnClickListener(view -> {
            currentIndex = (currentIndex + 1) % images.length;
            imageSwitcher.setImageResource(images[currentIndex]);
        });

        // DatePicker dialog logic
        Button btnPickDate = findViewById(R.id.btnPickDate);
        btnPickDate.setOnClickListener(view -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                    (view1, year, month, dayOfMonth) -> tvSelectedDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year),
                    Calendar.getInstance().get(Calendar.YEAR),
                    Calendar.getInstance().get(Calendar.MONTH),
                    Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
            datePickerDialog.show();
        });

        // Handle submit button to pass data to BrochureActivity
        Button btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, BrochureActivity.class);

            // Passing data to the next activity
            intent.putExtra("eventName", editEventName.getText().toString());
            intent.putExtra("eventMode", switchEventMode.isChecked());
            intent.putExtra("eventDate", tvSelectedDate.getText().toString());
            intent.putExtra("eventRating", ratingBar.getRating());

            // Start BrochureActivity
            startActivity(intent);
        });
    }
}
