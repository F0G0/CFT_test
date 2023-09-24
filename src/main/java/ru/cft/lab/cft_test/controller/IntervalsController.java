package ru.cft.lab.cft_test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.cft.lab.cft_test.dto.NumbersDto;
import ru.cft.lab.cft_test.exception.IntervalNotFoundException;
import ru.cft.lab.cft_test.mappings.Mappings;
import ru.cft.lab.cft_test.service.NumberService;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping(value = "/api/v1/intervals", produces = APPLICATION_JSON_VALUE)
public class IntervalsController {
    private final NumberService numberService;

    @Autowired
    public IntervalsController(NumberService numberService) {
        this.numberService = numberService;
    }

    @PostMapping(value = "merge", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> send(@RequestParam String kind, @RequestBody ArrayList<ArrayList<Integer>> intervals){
        switch (kind){
            case "digits" -> {
                this.numberService.createInterval(Mappings.ArraytoNumberDto(intervals));
                this.numberService.mergeIntervals();
            }
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping(value = "min")
    public ResponseEntity<List> min(@RequestParam String kind) {
        switch (kind){
            case "digits" -> {
                NumbersDto min  = this.numberService.min();
                return ResponseEntity.ok(min.getInterval());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
