package ru.cft.lab.cft_test.dto;

import java.util.ArrayList;

public class NumbersDto {

    private ArrayList<Integer> interval;

    public NumbersDto(ArrayList<Integer> interval) {
        this.interval = interval;
    }

    public NumbersDto(int start, int end) {
        this.interval = new ArrayList<Integer>();
        this.interval.add(start);
        this.interval.add(end);
    }
    public ArrayList<Integer> getInterval() {
        return interval;
    }
    public int getStart() {
        return interval.get(0);
    }

    public int getEnd() {
        return interval.get(1);
    }
}
