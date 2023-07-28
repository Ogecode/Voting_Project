package com.ogbonnaya.VotingProject5.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contestants")
public class Contestant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "contestantName")
    private String contestantName;

    @OneToMany (mappedBy = "contestant", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Vote> votes = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;


}
