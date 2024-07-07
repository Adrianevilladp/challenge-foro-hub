package com.alurachallenge.forohub.controller;

import com.alurachallenge.forohub.dto.ForoMapper;
import com.alurachallenge.forohub.dto.TopicDTO;
import com.alurachallenge.forohub.entity.Topic;
import com.alurachallenge.forohub.payload.CreateTopicRequest;
import com.alurachallenge.forohub.security.UserPrincipal;
import com.alurachallenge.forohub.service.TopicService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topic")
@AllArgsConstructor
@Validated
@SecurityRequirement(name = "bearer-key")
public class TopicController {
    private TopicService topicService;

    @PostMapping("/")
    public ResponseEntity<TopicDTO> create(@RequestBody @Valid CreateTopicRequest request,
                                           UriComponentsBuilder uriComponentsBuilder,
                                           @AuthenticationPrincipal UserPrincipal userPrincipal) {
        Topic topicSaved = topicService.createTopic(request, userPrincipal.getEmail());
        TopicDTO topicDTO = ForoMapper
                .INSTANCE
                .fromEntityToDTO(topicSaved);

        URI location = uriComponentsBuilder
                .path("/topic/{id}")
                .buildAndExpand(topicSaved.getId())
                .toUri();

        return ResponseEntity.created(location).body(topicDTO);
    }

    @GetMapping("/")
    public ResponseEntity<Page<TopicDTO>> getAll(@PageableDefault(size = 3,sort = "creationDate")
                                                     Pageable pageable) {
        Page<Topic> topicPage = topicService
                .getAllTopics(pageable);
        Page<TopicDTO> topicDTOPage = topicPage
                .map(ForoMapper.INSTANCE::fromEntityToDTO);

        return ResponseEntity.ok(topicDTOPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicDTO> getById(@PathVariable(name = "id") Long id) {

        Topic topicSaved = topicService
                .getById(id);
        TopicDTO topicDTO = ForoMapper
                .INSTANCE
                .fromEntityToDTO(topicSaved);

        return ResponseEntity.ok(topicDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicDTO> update(@PathVariable(name = "id") Long id,
                                           @RequestBody @Valid CreateTopicRequest request,
                                           @AuthenticationPrincipal UserPrincipal userPrincipal) {
        Topic topicSaved = topicService.updateById(id, request, userPrincipal.getEmail());
        TopicDTO topicDTO = ForoMapper
                .INSTANCE
                .fromEntityToDTO(topicSaved);

        return ResponseEntity.ok(topicDTO);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id) {
        topicService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
