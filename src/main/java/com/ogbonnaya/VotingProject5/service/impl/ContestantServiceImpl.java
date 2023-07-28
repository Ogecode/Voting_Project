package com.ogbonnaya.VotingProject5.service.impl;

import com.ogbonnaya.VotingProject5.controller.ContestantController;
import com.ogbonnaya.VotingProject5.entity.Contestant;
import com.ogbonnaya.VotingProject5.entity.Position;
import com.ogbonnaya.VotingProject5.exception.ResourceNotFoundException;
import com.ogbonnaya.VotingProject5.exception.VoteApiException;
import com.ogbonnaya.VotingProject5.payload.ContestantDto;
import com.ogbonnaya.VotingProject5.repository.ContestantRepository;
import com.ogbonnaya.VotingProject5.repository.PositionRepository;
import com.ogbonnaya.VotingProject5.service.ContestantService;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ContestantServiceImpl implements ContestantService {

    private ContestantRepository contestantRepository;

    private PositionRepository positionRepository;

    public ContestantServiceImpl(ContestantRepository contestantRepository, PositionRepository positionRepository) {
        this.contestantRepository = contestantRepository;
        this.positionRepository = positionRepository;
    }

    @Override
    public ContestantDto createContestant(long positionId, ContestantDto contestantDto) {

        Contestant contestant = mapToEntity(contestantDto);
        Position position = positionRepository.findById(positionId).orElseThrow(()-> new ResourceNotFoundException("Position", "id", positionId));

        contestant.setPosition(position);

        Contestant newContestant = contestantRepository.save(contestant);
       return mapToDto(newContestant);


    }

    @Override
    public List<ContestantDto> getContestantByPositionId(long positionId) {

        List<Contestant> contestants = contestantRepository.findByPositionId(positionId);
        return contestants.stream().map(contestant -> mapToDto(contestant)).collect(Collectors.toList());
    }

    @Override
    public ContestantDto getContestantById(Long positionId, Long contestantId) {
        Position position = positionRepository.findById(positionId)
                .orElseThrow(()-> new ResourceNotFoundException("Position", "id", positionId));

        Contestant contestant = contestantRepository.findById(contestantId)
                .orElseThrow(()-> new ResourceNotFoundException("Contestant", "id", contestantId));

        if(!Objects.equals(contestant.getPosition().getId(), position.getId())){

            throw  new VoteApiException(HttpStatus.BAD_REQUEST,"Contestant does not belong to position");
        }
       return  mapToDto(contestant);
    }

    @Override
    public ContestantDto updateContestant(Long positionId, long contestantId, ContestantDto contestantRequest) {
        Position position = positionRepository.findById(positionId)
                .orElseThrow(()-> new ResourceNotFoundException("Position", "id", positionId));

        Contestant contestant = contestantRepository.findById(contestantId)
                .orElseThrow(()-> new ResourceNotFoundException("Contestant", "id", contestantId));

        if(!Objects.equals(contestant.getPosition().getId(), position.getId())){
            throw  new VoteApiException(HttpStatus.BAD_REQUEST,"Contestant does not belong to position");
        }

        contestant.setContestantName(contestantRequest.getContestantName());

        Contestant updatedContestant = contestantRepository.save(contestant);
        return  mapToDto(updatedContestant);
    }

    @Override
    public void deleteContestant(Long positionId, Long contestantId) {
        Position position = positionRepository.findById(positionId)
                .orElseThrow(()-> new ResourceNotFoundException("Position", "id", positionId));

        Contestant contestant = contestantRepository.findById(contestantId)
                .orElseThrow(()-> new ResourceNotFoundException("Contestant", "id", contestantId));

        if(!Objects.equals(contestant.getPosition().getId(), position.getId())){
            throw  new VoteApiException(HttpStatus.BAD_REQUEST,"Contestant not found");
        }
    }

    private ContestantDto mapToDto (Contestant contestant){
        ContestantDto contestantDto = new ContestantDto();
        contestantDto.setId(contestant.getId());
        contestantDto.setContestantName(contestant.getContestantName());

        return contestantDto;
    }

    private  Contestant mapToEntity (ContestantDto contestantDto){
        Contestant contestant =new Contestant();
        contestant.setId(contestantDto.getId());
        contestant.setContestantName(contestantDto.getContestantName());

        return contestant;
    }
}
