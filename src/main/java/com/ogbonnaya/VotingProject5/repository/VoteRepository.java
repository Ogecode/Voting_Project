package com.ogbonnaya.VotingProject5.repository;

import com.ogbonnaya.VotingProject5.entity.Contestant;
import com.ogbonnaya.VotingProject5.entity.Position;
import com.ogbonnaya.VotingProject5.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoteRepository  extends JpaRepository<Vote, Long> {

    List<Vote> findByMemberId (long memberId);

    List<Vote> findByContestantId (long contestantId);

}
