package com.example.exam.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.exam.R;
import com.example.exam.dto.TravelDto;

import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TravelListAdapter extends ArrayAdapter<TravelDto> {

    private static final DateTimeFormatter DAY_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yy");

    public TravelListAdapter(Activity context, List<TravelDto> masterData) {
        super(context, R.layout.travel_list_item, masterData);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater
                    .from(getContext())
                    .inflate(R.layout.travel_list_item, parent, false);
        }
        TextView datesTextView = view.findViewById(R.id.dates);
        TextView durationTextView = view.findViewById(R.id.duration);
        TextView destinationTextView = view.findViewById(R.id.destination);


        TravelDto travelDto = getItem(position);
        datesTextView.setText(prepareDatesString(travelDto));
        durationTextView.setText(prepareDurationString(travelDto));
        destinationTextView.setText(prepareDestination(travelDto));
        return view;
    }

    private String prepareDatesString(TravelDto travelDto) {
        String template = getContext().getResources().getString(R.string.dates);
        return String.format(template, DAY_FORMATTER.format(travelDto.getFromDate()), DAY_FORMATTER.format(travelDto.getToDate()));
    }

    private String prepareDurationString(TravelDto travelDto) {
        String template = getContext().getResources().getString(R.string.duration);
        long days = Duration.between(travelDto.getFromDate().atStartOfDay(), travelDto.getToDate().atStartOfDay()).toDays();
        String daysStr = resolveEnding(days);
        return String.format(template, days, daysStr);
    }

    private String prepareDestination(TravelDto travelDto) {
        String template = getContext().getResources().getString(R.string.destination);
        return String.format(template, travelDto.getDestination());
    }

    private String resolveEnding(long days) {
        long l = days % 10;
        if ((l == 1) && (days != 11)) {
            return getContext().getResources().getString(R.string.day1);
        } else if ((l == 2 || l == 3 || l == 4) && (days != 13 && days != 12 && days != 14)) {
            return getContext().getResources().getString(R.string.day24);
        } else {
            return getContext().getResources().getString(R.string.day59);
        }
    }

}