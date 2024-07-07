package com.alurachallenge.forohub.payload;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public record CreateTopicRequest(
        @NotBlank
        String title,

        @NotBlank
        String message,

        @JsonAlias("course_title")
        @NotBlank
        String courseTitle
) {
}
