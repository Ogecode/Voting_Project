package com.ogbonnaya.VotingProject5.repository;

import com.ogbonnaya.VotingProject5.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository <Member, Long> {
}
