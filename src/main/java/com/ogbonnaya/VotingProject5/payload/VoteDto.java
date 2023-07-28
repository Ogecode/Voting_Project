package com.ogbonnaya.VotingProject5.payload;

import com.ogbonnaya.VotingProject5.entity.Contestant;
import com.ogbonnaya.VotingProject5.entity.Member;
import com.ogbonnaya.VotingProject5.entity.Position;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class VoteDto {

    private  long id;

    private int vote;


}
