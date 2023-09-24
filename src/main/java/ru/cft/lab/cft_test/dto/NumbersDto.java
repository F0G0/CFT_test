package ru.cft.lab.cft_test.dto;

import java.util.List;

public class NumbersDto {
    private int start;
    private int end;

    public NumbersDto(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}
