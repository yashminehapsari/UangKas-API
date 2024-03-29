package com.project.treasurerproject.controller;

import com.project.treasurerproject.constant.AppPath;
import com.project.treasurerproject.entity.request.PeriodRequest;
import com.project.treasurerproject.entity.response.CommonResponse;
import com.project.treasurerproject.entity.Period;
import com.project.treasurerproject.service.PeriodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.PERIOD)
public class PeriodController {
    private final PeriodService periodService;

    @PostMapping(AppPath.CREATE)
    public ResponseEntity<?> create(@RequestBody PeriodRequest periodRequest) {
        Period newPeriod= periodService.create(periodRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.builder()
                        .data(newPeriod)
                        .message("New type of period successfully created")
                        .statusCode(HttpStatus.CREATED.value())
                        .build());
    }

    @GetMapping(AppPath.GET_BY_ID)
    public ResponseEntity<?> getById(@PathVariable String id) {
        Period found = periodService.getById(id);
        if (found!=null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(CommonResponse.builder()
                            .data(found)
                            .message("Matched id found")
                            .statusCode(HttpStatus.OK.value())
                            .build());
        }return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(CommonResponse.builder()
                        .message("Matched id not found")
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .build());
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .data(periodService.getAll())
                        .message("Showing all data")
                        .statusCode(HttpStatus.OK.value())
                        .build());
    }
    @PutMapping(AppPath.UPDATE)
    public ResponseEntity<?> update(@RequestBody PeriodRequest periodRequest) {
        Period newPeriod = periodService.update(periodRequest);
        if (newPeriod!=null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(CommonResponse.builder()
                            .data(newPeriod)
                            .message("The data has successfully updated")
                            .statusCode(HttpStatus.OK.value())
                            .build());

        }return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(CommonResponse.builder()
                        .message("Matched id not found")
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .build());
    }

    @DeleteMapping(AppPath.DELETE_BY_ID)
    public ResponseEntity<?> delete(@PathVariable String id) {
        String found = periodService.deactivate(id);
        if (found!=null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(CommonResponse.builder()
                            .data(found)
                            .message("Data with matched id has successfully deleted")
                            .statusCode(HttpStatus.OK.value())
                            .build());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(CommonResponse.builder()
                        .message("Matched id not found")
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .build());
    }
}
