package com.example.exam;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.exam.adapter.TravelListAdapter;
import com.example.exam.dto.TravelDto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TravelListActivity extends AppCompatActivity {

    private final List<TravelDto> travels = new ArrayList<>();

    private static final String LOG_TAG = TravelListActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_list);
        updateAdapter();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            Log.d(LOG_TAG, "Activity result data is null");
            return;
        }
        TravelDto travel = (TravelDto) data.getSerializableExtra("travel");
        Log.d(LOG_TAG, "Activity result travel: " + travel);
        travels.add(travel);
        updateAdapter();
    }

    private void updateAdapter() {
        ListView mainList = findViewById(R.id.travel_list);
        List<TravelDto> sortedTravels = travels.stream()
                .sorted(Comparator.comparing(TravelDto::getFromDate))
                .collect(Collectors.toList());
        mainList.setAdapter(new TravelListAdapter(this, sortedTravels));

    }

    public void openAddDialog(View view) {
        Intent intent = new Intent(this, AddTravelActivity.class);
        startActivityForResult(intent, 0);
    }
}