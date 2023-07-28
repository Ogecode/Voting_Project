package com.ogbonnaya.VotingProject5.controller;

import com.ogbonnaya.VotingProject5.payload.VoteDto;
import com.ogbonnaya.VotingProject5.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class VoteController {
     private VoteService voteService;
     @Autowired

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

@PostMapping("/members/{memberId}/{contestantId}/votes")
    public ResponseEntity<VoteDto> createVote (@PathVariable(value = "memberId") long memberId,
                                               @PathVariable(value = "contestantId") long contestantId,
                                               @RequestBody VoteDto voteDto){
        return  new ResponseEntity<>(voteService.createVote(memberId,contestantId, voteDto), HttpStatus.CREATED);
    }


    @GetMapping("/members/{memberId}/{contestantId}/votes/{id}")
    public ResponseEntity<VoteDto> getVoteById (@PathVariable(value = "memberId") Long memberId,
                                                @PathVariable(value = "contestantId") Long contestantId,
                                                @PathVariable(value = "id") Long voteId){
         VoteDto voteDto = voteService.getVoteById(memberId, contestantId,voteId);
         return  new ResponseEntity<>(voteDto,HttpStatus.OK);
    }
    @GetMapping("/members/{contestantId}/votes/{id}")
    public List<VoteDto> getVoteByContestantId (@PathVariable (value = "contestantId") Long contestantId){
         return voteService.getVoteByContestantId(contestantId);
    }

    @GetMapping("/members/{memberId}/votes/{id}")
    public List<VoteDto> getVoteByMemberId (@PathVariable (value = "MemberId") Long memberId){
        return voteService.getVoteByMemberId(memberId);
    }
}
