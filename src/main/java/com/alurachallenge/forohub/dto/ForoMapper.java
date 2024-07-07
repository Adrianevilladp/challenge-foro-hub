package com.alurachallenge.forohub.dto;

import com.alurachallenge.forohub.entity.Course;
import com.alurachallenge.forohub.entity.Response;
import com.alurachallenge.forohub.entity.Topic;
import com.alurachallenge.forohub.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ForoMapper {
    ForoMapper INSTANCE = Mappers.getMapper(ForoMapper.class);

    TopicDTO fromEntityToDTO(Topic topic);
    CourseDTO fromEntityToDTO(Course course);
    ResponseDTO fromEntityToDTO(Response response);
    UserDTO fromEntityToDTO(User user);




}