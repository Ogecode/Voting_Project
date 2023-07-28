package com.ogbonnaya.VotingProject5.service;

import com.ogbonnaya.VotingProject5.payload.MemberDto;

import java.util.List;

public interface MemberService {
    MemberDto createMember (MemberDto memberDto);

    List<MemberDto> getAllMembers();

    MemberDto getMemberById(Long id);

    MemberDto updateMember (MemberDto memberDto, Long id);

    void deleteMember(Long id);
}
