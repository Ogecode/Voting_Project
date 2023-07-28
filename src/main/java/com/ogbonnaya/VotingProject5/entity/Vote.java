package com.ogbonnaya.VotingProject5.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vote")
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(name = "Vote")
    private int vote;

    @Column(name = "Date")
    private LocalDateTime dateTime;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contestant_id", nullable = false)
    private Contestant contestant;

   /* @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id", nullable = false)
    private Position position;*/

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
