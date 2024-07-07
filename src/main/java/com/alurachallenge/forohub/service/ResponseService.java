package com.alurachallenge.forohub.service;

import com.alurachallenge.forohub.entity.Response;
import com.alurachallenge.forohub.payload.CreateResponseRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ResponseService {
    Response createResponse(CreateResponseRequest request, String email);
    Page<Response> getAllResponses(Pageable pageable);
    Response getById(Long id);
    void deleteById(Long id);
}
