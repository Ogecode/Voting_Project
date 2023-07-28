package com.ogbonnaya.VotingProject5.controller;

import com.ogbonnaya.VotingProject5.payload.ContestantDto;
import com.ogbonnaya.VotingProject5.payload.PositionDto;
import com.ogbonnaya.VotingProject5.service.PositionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class PositionController {

    private PositionService positionService;

    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    @PostMapping("/members/{contestantId}/positions")
    public ResponseEntity<PositionDto> createPosition(@PathVariable(value = "contestantId") Long contestantId,
                                                      @RequestBody PositionDto positionDto) {
        return new ResponseEntity<>(positionService.createPosition(contestantId, positionDto), HttpStatus.CREATED);
    }

    @GetMapping("/position/{positionId}/contestants/{id}")
    public ResponseEntity<PositionDto> getPositionById(@PathVariable(value = "contestantId") Long contestantId,
                                                       @PathVariable(value = "id") Long positionId) {
        PositionDto positionDto = positionService.getPositionById(contestantId, positionId);
        return new ResponseEntity<>(positionDto, HttpStatus.OK);

    }

    @GetMapping("/positions/{contestantId}/position")
    public List<PositionDto> getPositionByContestantId(@PathVariable(value = "contestantId") Long contestantId) {
        return positionService.getPositionsByContestantId(contestantId);

    }

    public ResponseEntity<PositionDto> updatePosition (@PathVariable (value = "contestantId") Long contestantId,
                                             @PathVariable (value = "id") Long positionId,
                                             @RequestBody PositionDto positionDto){
        PositionDto updatedPosition = positionService.updatePosition(contestantId, positionId, positionDto);

        return new ResponseEntity<>(updatedPosition, HttpStatus.OK);
    }



    @DeleteMapping("/position/{positionId}/contestants/{id}")
    public ResponseEntity<String> deletePosition(@PathVariable (value = "contestantId") Long contestantId,
                                                   @PathVariable (value = "id") Long positionId){
    positionService.deletePosition(positionId, contestantId);
        return  new ResponseEntity<>("position has been successfully deleted", HttpStatus.OK);
    }
}