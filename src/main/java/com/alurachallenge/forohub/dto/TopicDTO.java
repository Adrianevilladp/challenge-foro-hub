package com.alurachallenge.forohub.dto;

import com.alurachallenge.forohub.enums.TopicStatus;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
public class TopicDTO {
    private Long id;
    private String title;
    private String message;
    @JsonProperty("creation_date")
    private LocalDateTime creationDate;
    @JsonProperty("topic_status")
    private TopicStatus topicStatus;
    private CourseDTO course;
    private UserDTO author;
    private Set<ResponseDTO> responses;

}
