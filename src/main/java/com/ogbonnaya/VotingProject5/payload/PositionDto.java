package com.ogbonnaya.VotingProject5.payload;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class PositionDto {


    private long id;

    private String positionName;

}
