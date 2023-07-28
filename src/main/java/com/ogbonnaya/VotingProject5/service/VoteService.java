package com.ogbonnaya.VotingProject5.service;

import com.ogbonnaya.VotingProject5.payload.VoteDto;

import java.util.List;

public interface VoteService {
    VoteDto createVote  (long contestantId, long memberId, VoteDto voteDto);

    List<VoteDto> getVoteByContestantId (long contestantId);

    List<VoteDto> getVoteByMemberId (long memberId);

    VoteDto getVoteById (Long contestantId, Long memberId, Long voteId );


}
