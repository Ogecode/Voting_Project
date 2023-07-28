package com.ogbonnaya.VotingProject5.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "position")
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "PositionName")
    private String positionName;

    /*@OneToMany(mappedBy = "position", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Vote> votes = new HashSet<>();*/


    @OneToMany(mappedBy = "position", cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "contestant_id")
    private Contestant contestant;
}
