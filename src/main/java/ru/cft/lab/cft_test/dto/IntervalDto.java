package ru.cft.lab.cft_test.dto;

import java.util.ArrayList;

public class IntervalDto<T> {
    protected ArrayList<T> interval;

    public IntervalDto(ArrayList<T> interval) {
        this.interval = interval;
    }

    public IntervalDto() {
    }

    public ArrayList<T> getInterval() {
        return interval;
    }
    public T getStart() {
        return interval.get(0);
    }

    public T getEnd() {
        return interval.get(1);
    }
}
