package com.project.treasurerproject.controller;

import com.project.treasurerproject.constant.AppPath;
import com.project.treasurerproject.entity.request.MemberRequest;
import com.project.treasurerproject.entity.response.CommonResponse;
import com.project.treasurerproject.entity.Member;
import com.project.treasurerproject.entity.response.MemberResponse;
import com.project.treasurerproject.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.MEMBER)
public class MemberController {
    public final MemberService memberService;

    @PostMapping(AppPath.CREATE)
    public ResponseEntity<?> create(@RequestBody MemberRequest memberRequest) {
        MemberResponse newMember= memberService.create(memberRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.builder()
                        .data(newMember)
                        .message("New member successfully created")
                        .statusCode(HttpStatus.CREATED.value())
                        .build());
    }

    @GetMapping(AppPath.GET_BY_ID)
    public ResponseEntity<?> getById(@PathVariable String id) {
        MemberResponse found = memberService.getById(id);
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
                        .data(memberService.getAll())
                        .message("Showing all data")
                        .statusCode(HttpStatus.OK.value())
                        .build());
    }
    @PutMapping(AppPath.UPDATE)
    public ResponseEntity<?> update(@RequestBody MemberRequest memberRequest) {
        MemberResponse newMember= memberService.update(memberRequest);
        if (newMember!=null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(CommonResponse.builder()
                            .data(newMember)
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
        String found = memberService.deactivate(id);
        if (found!=null) {
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
