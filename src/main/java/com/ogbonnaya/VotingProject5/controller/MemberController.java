package com.ogbonnaya.VotingProject5.controller;


import com.ogbonnaya.VotingProject5.payload.MemberDto;
import com.ogbonnaya.VotingProject5.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class MemberController {

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    private MemberService memberService;

    //create Memeber
    @PostMapping
    public ResponseEntity<MemberDto> createMember (@RequestBody MemberDto memberDto){
        return  new ResponseEntity<>(memberService.createMember(memberDto), HttpStatus.CREATED);
    }

    @GetMapping
    public List<MemberDto> getAllMembers(){
        return  memberService.getAllMembers();
}
@GetMapping("/{id}")
    public ResponseEntity<MemberDto> getMemberById (@PathVariable (name = "id") long id){
        return ResponseEntity.ok(memberService.getMemberById(id));
    }

    @PutMapping("/{id}")
    public  ResponseEntity<MemberDto> updateMember(@RequestBody MemberDto memberDto,
                                                   @PathVariable(name = "id") long id){
        MemberDto memberResponse = memberService.updateMember(memberDto, id);
        return  new ResponseEntity<>(memberResponse,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<String> deleteMember (@PathVariable(name = "id") long id){
        memberService.deleteMember(id);
        return new ResponseEntity<>("Member has been successfully deleted", HttpStatus.OK);
    }
}
