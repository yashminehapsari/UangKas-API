package com.project.treasurerproject.service;

import com.project.treasurerproject.entity.Member;
import com.project.treasurerproject.entity.request.MemberRequest;
import com.project.treasurerproject.entity.response.MemberResponse;

import java.util.List;

public interface MemberService {
    MemberResponse create(MemberRequest memberRequest);
    Member usualGetById(String id);
    MemberResponse getById(String id);
    List<MemberResponse> getAll();
    MemberResponse update(MemberRequest memberRequest);
    String deactivate(String id);
}
