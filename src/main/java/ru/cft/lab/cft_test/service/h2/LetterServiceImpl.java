package ru.cft.lab.cft_test.service.h2;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.stereotype.Service;
import ru.cft.lab.cft_test.dto.LettersDto;
import ru.cft.lab.cft_test.dto.NumbersDto;
import ru.cft.lab.cft_test.repository.LettersRepository;
import ru.cft.lab.cft_test.repository.entity.Letters;
import ru.cft.lab.cft_test.repository.entity.Letters;
import ru.cft.lab.cft_test.repository.entity.Numbers;
import ru.cft.lab.cft_test.service.LetterService;
import ru.cft.lab.cft_test.utils.Utils;

import java.util.*;
@Service("H2LetterService")
public class LetterServiceImpl implements LetterService {
    private final ObjectIdGenerators.UUIDGenerator uuidGenerator;
    private final LettersRepository lettersRepository;

    public LetterServiceImpl( LettersRepository lettersRepository) {
        this.uuidGenerator = new ObjectIdGenerators.UUIDGenerator();
        this.lettersRepository = lettersRepository;
    }

    @Override
    public void createInterval(List<LettersDto> intervals) {
        Letters letters = new Letters();
        UUID id = this.uuidGenerator.generateId(null);

        for(LettersDto interval:intervals) {
            letters.setId(id);
            letters.setStart(interval.getStart());
            letters.setEnd(interval.getEnd());
            this.lettersRepository.save(letters);
            id = this.uuidGenerator.generateId(null);
        }
    }

    @Override
    public void createIntervalAndMerge(List<LettersDto> intervalsDto) {
        List<Letters> intervals = new ArrayList<>();
        UUID id = this.uuidGenerator.generateId(null);
        for(LettersDto interval:intervalsDto) {
            Letters letters = new Letters();
            letters.setStart(interval.getStart());
            letters.setEnd(interval.getEnd());
            intervals.add(letters);
        }
        Collections.sort(intervals, new Comparator() {

            public int compare(Object o1, Object o2) {

                String s1 = ((Letters) o1).getStart();
                String s2 = ((Letters) o2).getStart();
                int sComp = s1.compareTo(s2);

                if (sComp != 0) {
                    return sComp;
                }

                String e1 = ((Letters) o1).getEnd();
                String e2 = ((Letters) o2).getEnd();
                return e1.compareTo(e2);
            }});
        Utils.mergeLetters(intervals);
        for(Letters interval:intervals) {
            interval.setId(id);
            this.lettersRepository.save(interval);
            id = this.uuidGenerator.generateId(null);
        }
    }

    @Override
    public void mergeIntervals() {
        List<Letters> intervals = list();
        List<Letters> arr = Utils.makeCopyLet(intervals);
        if(intervals.isEmpty()) return;
        int index = 0;
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(index).getEnd().charAt(0) >= arr.get(i).getStart().charAt(0)) {
                char maximum = (char) Math.max(arr.get(index).getEnd().charAt(0),
                        arr.get(i).getEnd().charAt(0));
                arr.get(index).setEnd(String.valueOf(maximum));
            }
            else {
                index++;
                arr.set(index, arr.get(i));
            }
        }
        arr = arr.subList(0,index+1);
        for (Letters old: intervals) {
            if(!arr.contains(old))
                lettersRepository.deleteById(old.getId());
        }
        for(Letters neww: arr){
            if(!intervals.contains(neww)){
                lettersRepository.save(neww);
            }
        }
    }

    @Override
    public LettersDto min() {
        Letters letters = new Letters();
        letters = lettersRepository.findTopByOrderByStartAscEndAsc();
        if(letters == null) return new LettersDto(new ArrayList<String>());
        return new LettersDto(letters.getStart(), letters.getEnd());
    }

    @Override
    public List<Letters> list() {
        return (List<Letters>)lettersRepository.getAllByOrderByStartAsc();
    }
}
