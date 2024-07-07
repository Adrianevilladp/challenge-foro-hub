package com.alurachallenge.forohub.controller;

import com.alurachallenge.forohub.dto.ForoMapper;
import com.alurachallenge.forohub.dto.ResponseDTO;
import com.alurachallenge.forohub.entity.Response;
import com.alurachallenge.forohub.payload.CreateResponseRequest;
import com.alurachallenge.forohub.security.UserPrincipal;
import com.alurachallenge.forohub.service.ResponseService;
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
@RequestMapping("/responses")
@AllArgsConstructor
@Validated
@SecurityRequirement(name = "bearer-key")
public class ResponseController {
    private ResponseService responseService;

    @PostMapping
    public ResponseEntity<ResponseDTO> createResponse(@RequestBody @Valid CreateResponseRequest createResponseRequest,
                                                      @AuthenticationPrincipal UserPrincipal userPrincipal,
                                                      UriComponentsBuilder uriComponentsBuilder) {
        Response response = responseService
                .createResponse(createResponseRequest, userPrincipal.getEmail());
        ResponseDTO responseDTO = ForoMapper
                .INSTANCE
                .fromEntityToDTO(response);

        URI location = uriComponentsBuilder
                .path("/responses/{id}")
                .buildAndExpand(responseDTO.getId())
                .toUri();

        return ResponseEntity.created(location).body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<Page<ResponseDTO>> getAllResponses(@PageableDefault(size = 3, sort = "creationDate")
                                                             Pageable pageable) {
        Page<Response> responses = responseService
                .getAllResponses(pageable);
        Page<ResponseDTO> responseDTOS = responses
                .map(ForoMapper.INSTANCE::fromEntityToDTO);

        return ResponseEntity.ok(responseDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getResponseById(@PathVariable Long id) {
        Response response = responseService.getById(id);
        ResponseDTO responseDTO = ForoMapper
                .INSTANCE
                .fromEntityToDTO(response);

        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResponse(@PathVariable Long id) {
        responseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
