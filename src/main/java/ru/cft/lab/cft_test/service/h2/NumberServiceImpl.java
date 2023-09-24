package ru.cft.lab.cft_test.service.h2;


import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.stereotype.Service;
import ru.cft.lab.cft_test.dto.NumbersDto;
import ru.cft.lab.cft_test.repository.NumbersRepository;
import ru.cft.lab.cft_test.repository.entity.Numbers;
import ru.cft.lab.cft_test.service.NumberService;

import java.util.List;
import java.util.UUID;
@Service("H2NumberService")
public class NumberServiceImpl implements NumberService {
    private final ObjectIdGenerators.UUIDGenerator uuidGenerator;
    private final NumbersRepository numbersRepository;

    public NumberServiceImpl(NumbersRepository numbersRepository) {
        this.uuidGenerator = new ObjectIdGenerators.UUIDGenerator();
        this.numbersRepository = numbersRepository;
    }

    @Override
    public void createInterval(List<NumbersDto> intervals) {
        Numbers numbers = new Numbers();
        UUID id = this.uuidGenerator.generateId(null);

        for(NumbersDto interval:intervals) {
            numbers.setId(id);
            numbers.setStart(interval.getStart());
            numbers.setEnd(interval.getEnd());
            this.numbersRepository.save(numbers);
            id = this.uuidGenerator.generateId(null);
        }
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public List<NumbersDto> list() {
        return null;
    }
}
