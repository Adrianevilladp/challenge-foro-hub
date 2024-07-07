package com.alurachallenge.forohub.entity;

import com.alurachallenge.forohub.enums.TopicStatus;
import com.alurachallenge.forohub.payload.CreateTopicRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "topics")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String message;

    @Column(name ="creation_date", nullable = false)
    private LocalDateTime creationDate;

    @Enumerated(EnumType.STRING)
    @Column(name ="status", nullable = false)
    private TopicStatus topicStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(mappedBy = "topic",  cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Response> responses = new HashSet<>();

    public void addResponse(Response response) {
        responses.add(response);
        response.setTopic(this);
    }

    public void removeResponse(Response response) {
        responses.remove(response);
        response.setTopic(null);
    }

    public void removeResponses() {
        responses.forEach(response -> response.setTopic(null));
        responses.clear();
    }

    public void updateWith(CreateTopicRequest topic) {
        this.title = topic.title();
        this.message = topic.message();
    }
}
