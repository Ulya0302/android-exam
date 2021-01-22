package com.example.exam;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.exam.dto.TravelDto;

import java.time.LocalDate;

public class AddTravelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_travel_activity);
    }

    public void saveActivity(View view) {
        DatePicker fromDatePicker = findViewById(R.id.start_date);
        LocalDate fromDate = LocalDate.of(fromDatePicker.getYear(), fromDatePicker.getMonth() + 1, fromDatePicker.getDayOfMonth());

        DatePicker toDatePicker = findViewById(R.id.end_date);
        LocalDate toDate = LocalDate.of(toDatePicker.getYear(), toDatePicker.getMonth() + 1, toDatePicker.getDayOfMonth());

        TextView destinationView = findViewById(R.id.destination);
        String destination = destinationView.getText().toString();

        TravelDto travelDto = new TravelDto(fromDate, toDate, destination);

        Intent data = new Intent();
        data.putExtra("travel", travelDto);
        setResult(Activity.RESULT_OK, data);
        finish();
    }

    public void cancelActivity(View view) {
        setResult(Activity.RESULT_OK);
        finish();
    }
}
