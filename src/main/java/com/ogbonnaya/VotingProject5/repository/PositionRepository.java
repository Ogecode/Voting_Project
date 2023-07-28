package com.ogbonnaya.VotingProject5.repository;

import com.ogbonnaya.VotingProject5.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PositionRepository extends JpaRepository<Position, Long> {

    List<Position> findByContestantId (long positionId);
}
