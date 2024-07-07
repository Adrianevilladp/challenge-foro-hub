package com.alurachallenge.forohub.payload;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateResponseRequest (
        @JsonAlias("id_topic")
        @NotNull
        Long id,

        @NotBlank(message = "message too short, explain more please")
        String message
){

}
