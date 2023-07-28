package com.ogbonnaya.VotingProject5.service.impl;

import com.ogbonnaya.VotingProject5.entity.Contestant;
import com.ogbonnaya.VotingProject5.entity.Member;
import com.ogbonnaya.VotingProject5.entity.Position;
import com.ogbonnaya.VotingProject5.entity.Vote;
import com.ogbonnaya.VotingProject5.exception.ResourceNotFoundException;
import com.ogbonnaya.VotingProject5.exception.VoteApiException;
import com.ogbonnaya.VotingProject5.payload.VoteDto;
import com.ogbonnaya.VotingProject5.repository.ContestantRepository;
import com.ogbonnaya.VotingProject5.repository.MemberRepository;
import com.ogbonnaya.VotingProject5.repository.PositionRepository;
import com.ogbonnaya.VotingProject5.repository.VoteRepository;
import com.ogbonnaya.VotingProject5.service.VoteService;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class VoteServiceImpl implements VoteService {

    private VoteRepository voteRepository;
    private PositionRepository positionRepository;
    private ContestantRepository contestantRepository;
    private MemberRepository memberRepository;

    public VoteServiceImpl(VoteRepository voteRepository,
                           ContestantRepository contestantRepository, MemberRepository memberRepository) {
        this.voteRepository = voteRepository;
        this.contestantRepository = contestantRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public VoteDto createVote(long contestantId, long memberId, VoteDto voteDto) {
        Vote vote = mapToEntity(voteDto);
        Contestant contestant = contestantRepository.findById(contestantId).orElseThrow(()-> new ResourceNotFoundException("Contestant", "id", contestantId));
        Member member = memberRepository.findById(memberId).orElseThrow(()-> new ResourceNotFoundException("Member","id", memberId));
        vote.setMember(member);
        vote.setContestant(contestant);

        Vote newVote =voteRepository.save(vote);
        return mapToDto(newVote);
    }

    @Override
    public List<VoteDto> getVoteByContestantId(long contestantId) {
        List<Vote> votes = voteRepository.findByContestantId(contestantId);
        return votes.stream().map(vote -> mapToDto(vote)).collect(Collectors.toList());
    }

    @Override
    public List<VoteDto> getVoteByMemberId(long memberId) {
        List<Vote> votes = voteRepository.findByMemberId(memberId);
        return votes.stream().map(vote -> mapToDto(vote)).collect(Collectors.toList());
    }

    @Override
    public VoteDto getVoteById(Long contestantId, Long memberId, Long voteId) {
        Contestant contestant = contestantRepository.findById(contestantId).orElseThrow(()-> new ResourceNotFoundException("Contestant", "id", contestantId));
        Member member = memberRepository.findById(memberId).orElseThrow(()-> new ResourceNotFoundException("Member","id", memberId));
        Vote vote = voteRepository.findById(voteId).orElseThrow(()-> new ResourceNotFoundException("Vote", "id", voteId));
            if(!Objects.equals(vote.getContestant().getId() ,contestant.getId())){
                throw new VoteApiException(HttpStatus.BAD_REQUEST, "Vote does not belong to contestant");
            }
            else if (!Objects.equals(vote.getMember().getId(), member.getId())){
            throw new VoteApiException(HttpStatus.BAD_REQUEST, "Vote does not belong to member");
        }

        return mapToDto(vote);
    }


    private VoteDto mapToDto (Vote vote){
        VoteDto voteDto = new VoteDto();
        voteDto.setVote(vote.getVote());

        return voteDto;
    }

    private Vote mapToEntity (VoteDto voteDto){
        Vote vote = new Vote();
        vote.setVote(voteDto.getVote());

        return vote;
    }
}
