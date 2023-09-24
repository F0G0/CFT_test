package ru.cft.lab.cft_test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.cft.lab.cft_test.mappings.Mappings;
import ru.cft.lab.cft_test.service.NumberService;

import java.util.ArrayList;

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
    public ResponseEntity<Void> merge(@RequestParam String kind, @RequestBody ArrayList<ArrayList<Integer>> intervals){
        switch (kind){
            case "digits" -> {
                this.numberService.createInterval(Mappings.toNumberDto(intervals));
            }
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
