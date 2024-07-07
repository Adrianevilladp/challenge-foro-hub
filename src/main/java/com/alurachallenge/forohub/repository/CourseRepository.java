package com.alurachallenge.forohub.repository;

import com.alurachallenge.forohub.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {


    Optional<Course> findCourseByNameContainingIgnoreCase(String s);
    Optional<Course> findByName(String s);
}
