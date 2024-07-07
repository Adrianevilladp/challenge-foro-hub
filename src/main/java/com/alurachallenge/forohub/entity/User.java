package com.alurachallenge.forohub.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "authors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "author_profiles",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "profile_id")
    )
    private Set<Profile> profiles = new HashSet<>();

    @OneToMany(mappedBy = "author",  cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Topic> topics = new HashSet<>();

    @OneToMany(mappedBy = "author",  cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Response> responses = new HashSet<>();


    public void addTopic(Topic topic) {
        topics.add(topic);
        topic.setAuthor(this);
    }

    public void addResponse(Response response) {
        responses.add(response);
        response.setAuthor(this);
    }

    public void removeTopic(Topic topic) {
        topics.remove(topic);
        topic.setAuthor(null);
    }

    public void removeTopic(Response response) {
        responses.remove(response);
        response.setAuthor(null);
    }

    public void removeTopics() {
        topics.forEach(topic -> topic.setAuthor(null));
        topics.clear();
    }

    public void removeResponses() {
        responses.forEach(response -> response.setAuthor(null));
        responses.clear();
    }

}
