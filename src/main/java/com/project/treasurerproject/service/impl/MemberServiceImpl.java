package com.project.treasurerproject.service.impl;

import com.project.treasurerproject.entity.Member;
import com.project.treasurerproject.entity.request.MemberRequest;
import com.project.treasurerproject.entity.response.MemberResponse;
import com.project.treasurerproject.repository.MemberRepo;
import com.project.treasurerproject.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepo memberRepo;
    private static MemberResponse getMemberResponse(Member member) {
        return MemberResponse.builder()
                .id(member.getId())
                .name(member.getName())
                .position(member.getPosition())
                .phone(member.getPhone())
                .address(member.getAddress())
                .build();
    }

    @Override
    public MemberResponse create(MemberRequest memberRequest) {
        Member member = memberRepo.save(getMember(memberRequest));
        return getMemberResponse(member);
    }

    private static Member getMember(MemberRequest memberRequest) {
        return Member.builder()
                .id(memberRequest.getId())
                .name(memberRequest.getName())
                .position(memberRequest.getPosition())
                .phone(memberRequest.getPhone())
                .address(memberRequest.getAddress())
                .isActive(true)
                .build();
    }

    public Member usualGetById(String id) {
        return memberRepo.findById(id).orElse(null);
    }

    @Override
    public MemberResponse getById(String id) {
        Member member = usualGetById(id);
        if (member!=null) {
            return getMemberResponse(member);
        }
        return null;
    }

    @Override
    public List<MemberResponse> getAll() {
        List<Member> memberList= memberRepo.findAll();
        List<MemberResponse> memberResponseList = new ArrayList<>();
        for (Member member : memberList) {
            memberResponseList.add(getMemberResponse(member));
        }
        return memberResponseList;
    }

    @Override
    public MemberResponse update(MemberRequest memberRequest) {
        Member check = usualGetById(memberRequest.getId());
        if (check!=null) {
            return getMemberResponse(memberRepo.save(getMember(memberRequest)));
        }
        return null;
    }

    @Override
    public String deactivate(String id) {
        Member check = usualGetById(id);
        if (check!=null) {
            check.setIsActive(false);
            memberRepo.save(check);
            return "deactivated";
        }
        return null;
    }
}
