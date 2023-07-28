package com.ogbonnaya.VotingProject5.payload;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class ContestantDto {


    private long id;

    private String contestantName;
}
