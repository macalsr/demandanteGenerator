package com.next.demandanteGenerator.security.dto;

import lombok.Getter;

@Getter
public class ResponseDto {
    String status;

    String message;

    int time;

    public ResponseDto(String status, String message, int time) {
        this.status = status;
        this.message = message;
        this.time = time;
    }
}