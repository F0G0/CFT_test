package ru.cft.lab.cft_test.service;

import ru.cft.lab.cft_test.dto.NumbersDto;
import ru.cft.lab.cft_test.repository.entity.Numbers;

import java.util.List;


public interface NumberService {
    void createInterval(List<NumbersDto> intervals);
    void createIntervalAndMerge(List<NumbersDto> intervals);
    void mergeIntervals();
    NumbersDto min();
    List<Numbers> list();

}
