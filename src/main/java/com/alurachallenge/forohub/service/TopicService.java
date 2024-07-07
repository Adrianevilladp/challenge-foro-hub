package com.alurachallenge.forohub.service;

import com.alurachallenge.forohub.entity.Topic;
import com.alurachallenge.forohub.payload.CreateTopicRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TopicService {
    Topic createTopic(CreateTopicRequest topic, String email);
    Page<Topic> getAllTopics(Pageable pageable);
    Topic getById(Long id);
    Topic updateById(Long id, CreateTopicRequest topic, String email);
    void deleteById(Long id);
}
