package com.example.exam.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class TravelDto implements Serializable {
    private LocalDate fromDate;
    private LocalDate toDate;
    private String destination;

    public TravelDto(LocalDate fromDate, LocalDate toDate, String destination) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.destination = destination;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
