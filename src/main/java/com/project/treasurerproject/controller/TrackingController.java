package com.project.treasurerproject.controller;

import com.project.treasurerproject.constant.AppPath;
import com.project.treasurerproject.entity.request.TrackingRequest;
import com.project.treasurerproject.entity.response.CommonResponse;
import com.project.treasurerproject.entity.response.TrackingResponse;
import com.project.treasurerproject.service.TrackingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.TRACKING)
public class TrackingController {
    private final TrackingService trackingService;

    @PostMapping(AppPath.CREATE)
    public ResponseEntity<?> create(@RequestBody TrackingRequest trackingRequest) {
        TrackingResponse newTracking= trackingService.create(trackingRequest);
        if (newTracking!=null) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(CommonResponse.builder()
                            .data(newTracking)
                            .message("New tracking successfully created")
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
        TrackingResponse found = trackingService.getById(id);
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
                        .data(trackingService.getAll())
                        .message("Showing all data")
                        .statusCode(HttpStatus.OK.value())
                        .build());
    }

    @GetMapping(AppPath.GET_ACCUMULATION_ID)
    public ResponseEntity<?> getByAccumulationId(@PathVariable("accumulation-id") String accumulationId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .data(trackingService.getByAccumulationId(accumulationId))
                        .message("Showing all data by Accumulation")
                        .statusCode(HttpStatus.OK.value())
                        .build());
    }

    @GetMapping(AppPath.GET_MEMBER_ID)
    public ResponseEntity<?> getByMemberId(@PathVariable("member-id") String memberId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .data(trackingService.getByMemberId(memberId))
                        .message("Showing all data by Member")
                        .statusCode(HttpStatus.OK.value())
                        .build());
    }

    @PutMapping(AppPath.UPDATE)
    public ResponseEntity<?> update(@RequestBody TrackingRequest trackingRequest) {
        TrackingResponse newTracking= trackingService.update(trackingRequest);
        if (newTracking!=null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(CommonResponse.builder()
                            .data(newTracking)
                            .message("The data has successfully updated")
                            .statusCode(HttpStatus.OK.value())
                            .build());

        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(CommonResponse.builder()
                        .message("Matched id not found")
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .build());
    }

    @DeleteMapping(AppPath.DELETE_BY_ID)
    public ResponseEntity<?> delete(@PathVariable String id) {
        String check = trackingService.deactivate(id);
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
