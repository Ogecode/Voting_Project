package com.ogbonnaya.VotingProject5.service;

import com.ogbonnaya.VotingProject5.payload.ContestantDto;

import java.util.List;

public interface ContestantService {

    ContestantDto createContestant (long positionId, ContestantDto contestantDto);

    List<ContestantDto> getContestantByPositionId(long positionId);

    ContestantDto getContestantById (Long positionId, Long contestantId);

   ContestantDto updateContestant(Long postId, long contestantId, ContestantDto contestantRequest);

    void deleteContestant (Long positiontId, Long contestantId);
}
