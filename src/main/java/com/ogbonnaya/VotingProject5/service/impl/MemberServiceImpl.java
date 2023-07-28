package com.ogbonnaya.VotingProject5.service.impl;

import com.ogbonnaya.VotingProject5.entity.Member;
import com.ogbonnaya.VotingProject5.exception.ResourceNotFoundException;
import com.ogbonnaya.VotingProject5.payload.MemberDto;
import com.ogbonnaya.VotingProject5.repository.MemberRepository;
import com.ogbonnaya.VotingProject5.service.MemberService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class MemberServiceImpl implements MemberService {

    private MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public MemberDto createMember(MemberDto memberDto) {
        Member member = mapToEntity(memberDto);
        Member newMember = memberRepository.save(member);

        MemberDto memberResponse = mapToDto(newMember);
        return memberResponse;
    }

    @Override
    public List<MemberDto> getAllMembers() {
        List<Member> members = memberRepository.findAll();

        return members.stream().map(member -> mapToDto(member)).collect(Collectors.toList());
    }

    @Override
    public MemberDto getMemberById(Long id) {
        Member member= memberRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Member", "id", id));
        return mapToDto(member);
    }

    @Override
    public MemberDto updateMember(MemberDto memberDto, Long id) {

        Member member = memberRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Member", "id", id));
        member.setLastName(memberDto.getLastName());
        member.setFirstName(member.getFirstName());
        member.setEmail(member.getEmail());
        member.setTelephoneNo(memberDto.getTelephoneNo());

        Member updatedMember = memberRepository.save(member);
        return mapToDto(updatedMember);
    }

    @Override
    public void deleteMember(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Member", "id", id));
        memberRepository.delete(member);
    }


    //Mapping Member Entity to Dto
    private  MemberDto mapToDto (Member member){
        MemberDto memberDto = new MemberDto();
        memberDto.setFirstName(member.getFirstName());
        memberDto.setLastName(member.getLastName());
        memberDto.setAge(member.getAge());
        memberDto.setId(member.getId());
        memberDto.setEmail(member.getEmail());
        memberDto.setTelephoneNo(member.getTelephoneNo());

        return memberDto;
    }

    //Mapping Dto into Entity

    private Member mapToEntity (MemberDto memberDto){
        Member member = new Member();
        member.setAge(memberDto.getAge());
        member.setId(memberDto.getId());
        member.setEmail(memberDto.getEmail());
        member.setFirstName(memberDto.getFirstName());
        member.setLastName(memberDto.getLastName());
        member.setTelephoneNo(memberDto.getTelephoneNo());

        return member;
    }
}
