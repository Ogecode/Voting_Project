package com.ogbonnaya.VotingProject5.service;

import com.ogbonnaya.VotingProject5.payload.PositionDto;
import com.ogbonnaya.VotingProject5.payload.VoteDto;

import java.util.List;

public interface PositionService {

    PositionDto createPosition (long contestantId,PositionDto positionDto);

    List<PositionDto> getPositionsByContestantId (long contestantId);

    PositionDto getPositionById (Long positionId, Long contestantId );

    PositionDto updatePosition (Long contestantId, long positionId, PositionDto positionRequest);

    void deletePosition (Long contestantId, Long positionId);

}
