package ru.cft.lab.cft_test.mappings;

import ru.cft.lab.cft_test.dto.LettersDto;
import ru.cft.lab.cft_test.dto.NumbersDto;
import ru.cft.lab.cft_test.repository.entity.Numbers;

import java.util.ArrayList;
import java.util.List;

public class Mappings {
    public static List<NumbersDto> ArraytoNumberDto(ArrayList<ArrayList<Integer>> list){
        List<NumbersDto> arr = new ArrayList<>();
        for(ArrayList<Integer> iter:list){
            arr.add(new NumbersDto(iter));
        }
        return arr;
    }

    public static List<NumbersDto> NumbertoNumberDto(List<Numbers> list){
        List<NumbersDto> arr = new ArrayList<>();
        for(Numbers iter:list){
            arr.add(new NumbersDto(iter.getStart(),iter.getEnd()));
        }
        return arr;
    }
    public static List<LettersDto> ArraytoLetterDto(ArrayList<ArrayList<String>> list){
        List<LettersDto> arr = new ArrayList<>();
        for(ArrayList<String> iter:list){
            arr.add(new LettersDto(iter));
        }
        return arr;
    }
}
