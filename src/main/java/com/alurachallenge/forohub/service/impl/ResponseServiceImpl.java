package com.alurachallenge.forohub.service.impl;

import com.alurachallenge.forohub.constant.MessageConstant;
import com.alurachallenge.forohub.entity.Response;
import com.alurachallenge.forohub.entity.Topic;
import com.alurachallenge.forohub.entity.User;
import com.alurachallenge.forohub.exception.NotFoundEntityException;
import com.alurachallenge.forohub.payload.CreateResponseRequest;
import com.alurachallenge.forohub.repository.ResponseRepository;
import com.alurachallenge.forohub.repository.UserRepository;
import com.alurachallenge.forohub.service.ResponseService;
import com.alurachallenge.forohub.service.TopicService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
@Slf4j
public class ResponseServiceImpl implements ResponseService {
    private UserRepository userRepository;
    private TopicService topicService;
    private ResponseRepository responseRepository;

    @Override
    public Response createResponse(CreateResponseRequest request, String email) {
        Response responseToSave = new Response();
        responseToSave.setMessage(request.message());
        responseToSave.setCreationDate(LocalDateTime.now());

        User author = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new NotFoundEntityException(MessageConstant.USER_NOT_FOUND
                                .formatted(email)));
        author.addResponse(responseToSave);

        Topic topic = topicService.getById(request.id());
        topic.addResponse(responseToSave);

        return responseRepository.save(responseToSave);
    }

    @Override
    public Page<Response> getAllResponses(Pageable pageable) {
        return responseRepository.findAll(pageable);
    }

    @Override
    public Response getById(Long id) {
        return responseRepository.findById(id)
                .orElseThrow(() -> new NotFoundEntityException(
                        MessageConstant
                                .RESPONSE_NOT_FOUND.formatted(id)));
    }

    @Override
    public void deleteById(Long id) {
        Response responseToDelete = getById(id);
        log.info("Response with id {} is gonna be deleted", responseToDelete.getId());
        responseRepository.delete(responseToDelete);
    }
}
