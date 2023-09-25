package ru.cft.lab.cft_test.utils;

import ru.cft.lab.cft_test.dto.NumbersDto;
import ru.cft.lab.cft_test.repository.entity.Letters;
import ru.cft.lab.cft_test.repository.entity.Numbers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Utils {
    public static void mergeLetters(List<Letters> intervals) {
        if (intervals.isEmpty()) return;
        int index = 0;
        for (int i = 1; i < intervals.size(); i++) {
            if (intervals.get(index).getEnd().charAt(0) >= intervals.get(i).getStart().charAt(0)) {
                char maximum = (char) Math.max(intervals.get(index).getEnd().charAt(0),
                        intervals.get(i).getEnd().charAt(0));
                intervals.get(index).setEnd(String.valueOf(maximum));
            } else {
                index++;
                intervals.set(index, intervals.get(i));
            }
        }
        intervals.remove(index+1);
    }

    public static void mergeNumbers(List<Numbers> intervals) {
        if (intervals.isEmpty()) return;
        int index = 0;
        for (int i = 1; i < intervals.size(); i++) {
            if (intervals.get(index).getEnd() >= intervals.get(i).getStart()) {
                intervals.get(index).setEnd(Math.max(intervals.get(index).getEnd(),
                        intervals.get(i).getEnd()));
            } else {
                index++;
                intervals.set(index, intervals.get(i));
            }
        }
        intervals.remove(index+1);
    }

    public static List<Numbers> makeCopyNum(List<Numbers> list){
        List<Numbers> result = new ArrayList<>();
        for(Numbers interval:list) {
            Numbers numbers = new Numbers();
            numbers.setStart(interval.getStart());
            numbers.setEnd(interval.getEnd());
            numbers.setId(interval.getId());
            result.add(numbers);
        }
        return result;
    }
    public static List<Letters> makeCopyLet(List<Letters> list){
        List<Letters> result = new ArrayList<>();
        for(Letters interval:list) {
            Letters letters = new Letters();
            letters.setStart(interval.getStart());
            letters.setEnd(interval.getEnd());
            letters.setId(interval.getId());
            result.add(letters);
        }
        return result;
    }
}

