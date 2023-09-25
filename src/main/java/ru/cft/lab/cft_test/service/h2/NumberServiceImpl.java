package ru.cft.lab.cft_test.service.h2;


import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.stereotype.Service;
import ru.cft.lab.cft_test.dto.NumbersDto;
import ru.cft.lab.cft_test.repository.NumbersRepository;
import ru.cft.lab.cft_test.repository.entity.Numbers;
import ru.cft.lab.cft_test.service.NumberService;
import ru.cft.lab.cft_test.utils.Utils;

import java.util.*;

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
    public void createIntervalAndMerge(List<NumbersDto> intervalsDto) {
        List<Numbers> intervals = new ArrayList<>();
        UUID id = this.uuidGenerator.generateId(null);
        for(NumbersDto interval:intervalsDto) {
            Numbers numbers = new Numbers();
            numbers.setStart(interval.getStart());
            numbers.setEnd(interval.getEnd());
            intervals.add(numbers);
        }
        Collections.sort(intervals, new Comparator() {

            public int compare(Object o1, Object o2) {

                Integer s1 = ((Numbers) o1).getStart();
                Integer s2 = ((Numbers) o2).getStart();
                int sComp = s1.compareTo(s2);

                if (sComp != 0) {
                    return sComp;
                }

                Integer e1 = ((Numbers) o1).getEnd();
                Integer e2 = ((Numbers) o2).getEnd();
                return e1.compareTo(e2);
            }});
        Utils.mergeNumbers(intervals);
        for(Numbers interval:intervals) {
            interval.setId(id);
            this.numbersRepository.save(interval);
            id = this.uuidGenerator.generateId(null);
        }
    }

    @Override
    public void mergeIntervals() {
        List<Numbers> intervals = list();
        List<Numbers> arr = Utils.makeCopyNum(intervals);
        if(intervals.isEmpty()) return;
        int index = 0;
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(index).getEnd() >= arr.get(i).getStart()) {
                arr.get(index).setEnd(Math.max(arr.get(index).getEnd(),
                        arr.get(i).getEnd()));
            }
            else {
                index++;
                arr.set(index, arr.get(i));
            }
        }
        arr = arr.subList(0,index+1);
        for (Numbers old: intervals) {
            if(!arr.contains(old))
                numbersRepository.deleteById(old.getId());
        }
        for(Numbers neww: arr){
            if(!intervals.contains(neww)){
                numbersRepository.save(neww);
            }
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
