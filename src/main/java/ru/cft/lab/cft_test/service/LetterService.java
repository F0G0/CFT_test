package ru.cft.lab.cft_test.service;

import ru.cft.lab.cft_test.dto.LettersDto;
import ru.cft.lab.cft_test.dto.NumbersDto;
import ru.cft.lab.cft_test.repository.entity.Letters;

import java.util.List;

public interface LetterService {
    void createInterval(List<LettersDto> intervals);
    void createIntervalAndMerge(List<LettersDto> intervals);
    void mergeIntervals();
    LettersDto min();
    List<Letters> list();
}
