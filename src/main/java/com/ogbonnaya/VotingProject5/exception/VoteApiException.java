package com.ogbonnaya.VotingProject5.exception;

import org.springframework.http.HttpStatus;

public class VoteApiException extends RuntimeException{
    private HttpStatus status;
    private String message;

    public VoteApiException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public VoteApiException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
