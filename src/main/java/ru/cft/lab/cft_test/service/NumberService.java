package ru.cft.lab.cft_test.service;

import ru.cft.lab.cft_test.dto.NumbersDto;

import java.util.List;
import java.util.UUID;

public interface NumberService {
    void createInterval(List<NumbersDto> intervals);
    void delete (UUID id);
    List<NumbersDto> list();
}
