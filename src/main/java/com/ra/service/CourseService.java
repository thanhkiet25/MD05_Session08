package com.ra.service;

import com.ra.model.dto.request.CourseDTO;
import com.ra.model.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CourseService {
    Course addCourse(CourseDTO courseDTO);
    Course findById(long id);
    Course updateCourse( Long id,CourseDTO courseDTO);
    boolean deleteCourse(Long id);
    Page<Course> getAllCourse(Pageable pageable);
}
