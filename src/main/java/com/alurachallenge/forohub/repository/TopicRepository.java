package com.alurachallenge.forohub.repository;

import com.alurachallenge.forohub.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
    boolean existsByIdAndAuthorEmail(Long id, String email);
    boolean existsTopicByTitleAndMessage(String title, String message);

}
