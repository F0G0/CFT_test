package ru.cft.lab.cft_test.dto;

import java.util.ArrayList;

public class LettersDto extends IntervalDto<String> {
    public LettersDto(ArrayList<String> interval) {
        super(interval);
    }
    public LettersDto(String start, String end) {
        this.interval = new ArrayList<String>();
        this.interval.add(start);
        this.interval.add(end);
    }
}
