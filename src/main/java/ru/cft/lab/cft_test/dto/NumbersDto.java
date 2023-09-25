package ru.cft.lab.cft_test.dto;

import java.util.ArrayList;

public class NumbersDto extends IntervalDto<Integer>{

    public NumbersDto(ArrayList<Integer> interval) {
        super(interval);
    }

    public NumbersDto(int start, int end) {
        this.interval = new ArrayList<Integer>();
        this.interval.add(start);
        this.interval.add(end);
    }

}
