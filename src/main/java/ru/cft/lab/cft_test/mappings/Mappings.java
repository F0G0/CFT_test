package ru.cft.lab.cft_test.mappings;

import ru.cft.lab.cft_test.dto.NumbersDto;

import java.util.ArrayList;
import java.util.List;

public class Mappings {
    public static List<NumbersDto> toNumberDto(ArrayList<ArrayList<Integer>> list){
        List<NumbersDto> arr = new ArrayList<>();
        for(ArrayList<Integer> iter:list){
            arr.add(new NumbersDto(iter.get(0),iter.get(1)));
        }
        return arr;
    }
}
