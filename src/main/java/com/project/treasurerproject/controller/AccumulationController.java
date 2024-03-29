package com.project.treasurerproject.controller;

import com.project.treasurerproject.constant.AppPath;
import com.project.treasurerproject.entity.request.AccumulationRequest;
import com.project.treasurerproject.entity.response.AccumulationResponse;
import com.project.treasurerproject.entity.response.CommonResponse;
import com.project.treasurerproject.service.AccumulationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.ACCUMULATION)
public class AccumulationController {
    private final AccumulationService accumulationService;
    @PostMapping(AppPath.CREATE)
    public ResponseEntity<?> create(@RequestBody AccumulationRequest accumulationRequest) {
        AccumulationResponse newAccumulation= accumulationService.create(accumulationRequest);
        if (newAccumulation!=null) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(CommonResponse.builder()
                            .data(newAccumulation)
                            .message("New accumulation successfully created")
                            .statusCode(HttpStatus.CREATED.value())
                            .build());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(CommonResponse.builder()
                        .message("Matched id not found")
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .build());
    }

    @GetMapping(AppPath.GET_BY_ID)
    public ResponseEntity<?> getById(@PathVariable String id) {
        AccumulationResponse found = accumulationService.getById(id);
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
                        .data(accumulationService.getAll())
                        .message("Showing all data")
                        .statusCode(HttpStatus.OK.value())
                        .build());
    }

    @PutMapping(AppPath.UPDATE)
    public ResponseEntity<?> update(@RequestBody AccumulationRequest accumulationRequest) {
        AccumulationResponse newAccumulation = accumulationService.update(accumulationRequest);
        if (newAccumulation!=null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(CommonResponse.builder()
                            .data(newAccumulation)
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
        String check = accumulationService.deactivate(id);
        if (check!=null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(CommonResponse.builder()
                            .message("Data with matched id has successfully deactivated")
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
