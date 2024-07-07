package com.alurachallenge.forohub.dto;

import com.alurachallenge.forohub.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ResponseDTO {
    private Long id;
    private String message;
    @JsonProperty("creation_date")
    private LocalDateTime creationDate;
    private UserDTO author;
}
