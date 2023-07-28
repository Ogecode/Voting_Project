package com.ogbonnaya.VotingProject5.service.impl;

import com.ogbonnaya.VotingProject5.entity.Contestant;
import com.ogbonnaya.VotingProject5.entity.Position;
import com.ogbonnaya.VotingProject5.entity.Vote;
import com.ogbonnaya.VotingProject5.exception.ResourceNotFoundException;
import com.ogbonnaya.VotingProject5.exception.VoteApiException;
import com.ogbonnaya.VotingProject5.payload.PositionDto;
import com.ogbonnaya.VotingProject5.payload.VoteDto;
import com.ogbonnaya.VotingProject5.repository.ContestantRepository;
import com.ogbonnaya.VotingProject5.repository.PositionRepository;
import com.ogbonnaya.VotingProject5.repository.VoteRepository;
import com.ogbonnaya.VotingProject5.service.PositionService;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PositionServiceImpl implements PositionService {

   private PositionRepository positionRepository;
    private ContestantRepository contestantRepository;

    public PositionServiceImpl(PositionRepository positionRepository,
                               ContestantRepository contestantRepository) {
        this.positionRepository = positionRepository;
        this.contestantRepository = contestantRepository;
    }


    @Override
    public PositionDto createPosition(long contestantId, PositionDto positionDto) {
        Position position = mapToEntity(positionDto);
        Contestant contestant = contestantRepository.findById(contestantId).orElseThrow(()-> new ResourceNotFoundException("Contestant","id", contestantId));
        position.setContestant(contestant);
        Position newPosition = positionRepository.save(position);
        return  mapToDto(newPosition);
    }

    @Override
    public List<PositionDto> getPositionsByContestantId(long contestantId) {
        List<Position> positions = positionRepository.findByContestantId(contestantId);
        return positions.stream().map(position -> mapToDto(position)).collect(Collectors.toList());
    }

    @Override
    public PositionDto getPositionById(Long positionId, Long contestantId) {
        Contestant contestant = contestantRepository.findById(contestantId).orElseThrow(()->new ResourceNotFoundException("Contestant", "id", contestantId));
        Position position = positionRepository.findById(positionId).orElseThrow(()-> new ResourceNotFoundException("Position", "id", positionId));
            if(!Objects.equals(position.getContestant().getId(), contestant.getId())){
                throw new VoteApiException(HttpStatus.BAD_REQUEST, "Position does not belong to contestant");
        }

        return mapToDto(position);
    }

    @Override
    public PositionDto updatePosition(Long contestantId, long positionId, PositionDto positionRequest) {
        Contestant contestant = contestantRepository.findById(contestantId).orElseThrow(()->new ResourceNotFoundException("Contestant", "id", contestantId));
        Position position = positionRepository.findById(positionId).orElseThrow(()-> new ResourceNotFoundException("Position", "id", positionId));
            if(!Objects.equals(position.getContestant().getId(), contestant.getId())){
            throw new VoteApiException(HttpStatus.BAD_REQUEST, "Position does not belong to contestant");
        }
        position.setPositionName(positionRequest.getPositionName());
            Position updatedPosition = positionRepository.save(position);

        return mapToDto(updatedPosition);
    }


    @Override
    public void deletePosition(Long contestantId, Long positionId) {
        Contestant contestant = contestantRepository.findById(contestantId).orElseThrow(()->new ResourceNotFoundException("Contestant", "id", contestantId));
        Position position = positionRepository.findById(positionId).orElseThrow(()-> new ResourceNotFoundException("Position", "id", positionId));
            if(!Objects.equals(position.getContestant().getId(), contestant.getId())){
            throw new VoteApiException(HttpStatus.BAD_REQUEST, "Position does not belong to contestant");
        }

        positionRepository.delete(position);
    }



    private PositionDto mapToDto (Position position){
        PositionDto positionDto = new PositionDto();
        positionDto.setPositionName(position.getPositionName());

        return positionDto;

    }

    private Position mapToEntity (PositionDto positionDto){
        Position position = new Position();
        position.setPositionName(positionDto.getPositionName());
         return position;
    }
}
