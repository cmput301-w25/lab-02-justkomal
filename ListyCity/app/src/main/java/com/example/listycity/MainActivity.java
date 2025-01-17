package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;

    Button add_button;
    Button remove_button;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Initialize UI elements
        cityList = findViewById(R.id.city_list);
        editText = findViewById(R.id.editText);
        add_button = findViewById(R.id.add_button);
        remove_button = findViewById(R.id.remove_button);

        // Initialize data list and adapter
        String[] cities = {"Calgary", "Edmonton", "Moscow", "Paris", "Vienna"};
        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));
        cityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
        cityList.setAdapter(cityAdapter);

        // Add button functionality
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cityName = editText.getText().toString();
                if (!cityName.isEmpty() && !dataList.contains(cityName)) {
                    dataList.add(cityName);
                    cityAdapter.notifyDataSetChanged();
                    editText.setText(""); // Clear input
                }
            }
        });

        // Remove button functionality
        remove_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cityName = editText.getText().toString();
                if (!cityName.isEmpty() && dataList.contains(cityName)) {
                    dataList.remove(cityName);
                    cityAdapter.notifyDataSetChanged();
                    editText.setText(""); // Clear input
                }
            }
        });

        // Handle window insets for layout adjustments
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
