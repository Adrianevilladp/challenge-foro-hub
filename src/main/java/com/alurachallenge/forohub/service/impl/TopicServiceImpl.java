package com.alurachallenge.forohub.service.impl;

import com.alurachallenge.forohub.constant.MessageConstant;
import com.alurachallenge.forohub.entity.Course;
import com.alurachallenge.forohub.entity.Topic;
import com.alurachallenge.forohub.entity.User;
import com.alurachallenge.forohub.enums.TopicStatus;
import com.alurachallenge.forohub.exception.AlreadyExistsException;
import com.alurachallenge.forohub.exception.NotFoundEntityException;
import com.alurachallenge.forohub.exception.UnauthorizedUserException;
import com.alurachallenge.forohub.payload.CreateTopicRequest;
import com.alurachallenge.forohub.repository.CourseRepository;
import com.alurachallenge.forohub.repository.TopicRepository;
import com.alurachallenge.forohub.repository.UserRepository;
import com.alurachallenge.forohub.service.TopicService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class TopicServiceImpl implements TopicService {
    private UserRepository userRepository;
    private CourseRepository courseRepository;
    private TopicRepository topicRepository;

    @Override
    @Transactional
    public Topic createTopic(CreateTopicRequest topic, String email) {
        if (topicRepository.existsTopicByTitleAndMessage(topic.title(), topic.message())) {
            throw new AlreadyExistsException(MessageConstant
                    .TOPIC_ALREADY_EXISTS
                    .formatted(topic.title(), topic.message()));
        }

        Topic topicToSave = new Topic();
        topicToSave.setTitle(topic.title());
        topicToSave.setMessage(topic.message());
        topicToSave.setCreationDate(LocalDateTime.now());
        topicToSave.setTopicStatus(TopicStatus.PUBLISHED);

        User author = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new NotFoundEntityException(MessageConstant.USER_NOT_FOUND
                                .formatted(email)));
        author.addTopic(topicToSave);

        Course course = courseRepository.findCourseByNameContainingIgnoreCase(topic.courseTitle())
                .orElseThrow(() ->
                        new NotFoundEntityException(MessageConstant.COURSE_NOT_FOUND
                                .formatted(topic.courseTitle())));
        course.addTopic(topicToSave);


        return topicRepository.save(topicToSave);
    }

    @Override
    public Page<Topic> getAllTopics(Pageable pageable) {
        return topicRepository.findAll(pageable);
    }

    @Override
    public Topic getById(Long id) {
        return topicRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundEntityException(
                        MessageConstant.TOPIC_NOT_FOUND.formatted(id)));
    }

    @Override
    @Transactional
    public Topic updateById(Long id, CreateTopicRequest topic, String email) {
        if (!topicRepository.existsByIdAndAuthorEmail(id, email)) {
            throw new UnauthorizedUserException(MessageConstant.UNAUTHORIZED_MESSAGE);
        }
        Topic topicFound = getById(id);
        Course courseFound = courseRepository
                .findByName(topic.courseTitle())
                        .orElseThrow(() -> new NotFoundEntityException(MessageConstant.COURSE_NOT_FOUND));

        topicFound.updateWith(topic);
        topicFound.getCourse().removeTopic(topicFound);
        courseFound.addTopic(topicFound);

        return topicRepository.save(topicFound);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Topic topicToDelete = getById(id);
        log.info("Topic with title {} will be deleted", topicToDelete.getTitle());
        topicRepository.deleteById(id);
    }

}
