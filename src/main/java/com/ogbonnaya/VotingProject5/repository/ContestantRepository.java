package com.ogbonnaya.VotingProject5.repository;

import com.ogbonnaya.VotingProject5.entity.Contestant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContestantRepository extends JpaRepository<Contestant, Long> {

    List<Contestant> findByVoteId (long voteId);
    List<Contestant> findByPositionId (long positionId);
}
