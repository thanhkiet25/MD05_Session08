package com.ra.service.imp;

import com.ra.model.entity.Course;
import com.ra.model.entity.Enrollment;
import com.ra.model.entity.User;
import com.ra.repository.EnrollmentRepository;
import com.ra.service.CourseService;
import com.ra.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class EnrollmentServiceImp implements EnrollmentService {
    @Autowired
    private EnrollmentRepository  enrollmentRepository;
    @Autowired
    private CourseService courseService;
    @Override
    public Enrollment registerCourse(User user, Long courseId) {
        Course course = courseService.findById(courseId);
        if(course == null){
            return null;
        }else{
            Enrollment enrollment = new Enrollment();
            enrollment.setUser(user);
            enrollment.setCourse(course);
            enrollment.setCreatedDate(LocalDate.now());
            return enrollmentRepository.save(enrollment);
        }
    }

    @Override
    public List<Enrollment> getRegisteredCourses(Long userId) {
        return enrollmentRepository.findByUserId(userId);
    }
}
