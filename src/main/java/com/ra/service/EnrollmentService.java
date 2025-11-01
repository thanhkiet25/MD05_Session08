package com.ra.service;

import com.ra.model.entity.Enrollment;
import com.ra.model.entity.User;

import java.util.List;

public interface EnrollmentService {
    Enrollment registerCourse (User user, Long courseId);
    List<Enrollment> getRegisteredCourses(Long userId);
}
