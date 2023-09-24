package ru.cft.lab.cft_test.service.h2;


import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.stereotype.Service;
import ru.cft.lab.cft_test.dto.NumbersDto;
import ru.cft.lab.cft_test.exception.IntervalNotFoundException;
import ru.cft.lab.cft_test.mappings.Mappings;
import ru.cft.lab.cft_test.repository.NumbersRepository;
import ru.cft.lab.cft_test.repository.entity.Numbers;
import ru.cft.lab.cft_test.service.NumberService;

import java.util.ArrayList;
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
    public void mergeIntervals() {
        List<Numbers> intervals = list();
        List<Numbers> arr = new ArrayList<>(intervals);
        if(intervals.isEmpty()) return;
        int index = 0;
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(index).getEnd() >= arr.get(i).getStart()) {
                arr.get(index).setEnd(Math.max(arr.get(index).getEnd(), arr.get(i).getEnd()));
            }
            else {
                index++;
                arr.set(index, arr.get(i));
            }
        }
        arr = arr.subList(0,index+1);
        intervals.removeAll(arr);
        for (Numbers old: intervals) {
            numbersRepository.deleteById(old.getId());
        }

    }

    @Override
    public NumbersDto min() {
        Numbers numbers = new Numbers();
        numbers = numbersRepository.findTopByOrderByStartAscEndAsc();
        if(numbers == null) return new NumbersDto(new ArrayList<Integer>());
        return new NumbersDto(numbers.getStart(), numbers.getEnd());
    }

    @Override
    public List<Numbers> list() {
        return (List<Numbers>)numbersRepository.getAllByOrderByStartAsc();
    }
}
