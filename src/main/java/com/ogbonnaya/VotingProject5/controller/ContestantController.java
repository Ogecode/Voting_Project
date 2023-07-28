package com.ogbonnaya.VotingProject5.controller;

import com.ogbonnaya.VotingProject5.payload.ContestantDto;
import com.ogbonnaya.VotingProject5.service.ContestantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contestants")
public class ContestantController {

    private ContestantService  contestantService;

    public ContestantController(ContestantService contestantService) {
        this.contestantService = contestantService;
    }

    @PostMapping("/position/{positionId}/contestant")
    public ResponseEntity<ContestantDto> createContestant (@PathVariable (value = "positionId") long positionId,
                                                           @RequestBody ContestantDto contestantDto){
        return  new ResponseEntity<>(contestantService.createContestant(positionId,contestantDto), HttpStatus.CREATED);
    }

    @GetMapping("/contestants/{positionId}/contestant")
    public List<ContestantDto> getContestantByPositionId(@PathVariable (value = "positionId") long positionId){
        return  contestantService.getContestantByPositionId(positionId);

    }

    @GetMapping("/position/{positionId}/contestants/{id}")
    public ResponseEntity<ContestantDto> getContestantById(@PathVariable (value = "positionId") Long positionId,
                                                 @PathVariable (value = "id") Long contestantId){
        ContestantDto contestantDto = contestantService.getContestantById(positionId, contestantId);
        return new ResponseEntity<>(contestantDto, HttpStatus.OK);

    }

    @PutMapping("/position/{positionId}/contestants/{id}")
    public List<ContestantDto> updateContestant(@PathVariable (value = "positionId") Long positionId,
                                                 @PathVariable (value = "id") Long contestantId,
                                                    @RequestBody ContestantDto contestantDto){
        ContestantDto updatedContestant = contestantService.updateContestant(positionId, contestantId, contestantDto );
        return  contestantService.getContestantByPositionId(positionId);

    }

    @DeleteMapping("/position/{positionId}/contestants/{id}")
    public ResponseEntity<String> deleteContestant(@PathVariable (value = "positionId") Long positionId,
                                                 @PathVariable (value = "id") Long contestantId){
        contestantService.deleteContestant(positionId, contestantId);

        return  new ResponseEntity<>("contestant has been successfully deleted", HttpStatus.OK);
        }

}
