package com.ra.service.imp;

import com.ra.model.dto.request.CourseDTO;
import com.ra.model.entity.Course;
import com.ra.repository.CourseRepository;
import com.ra.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class CourseServiceImp implements CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Override
    public Course addCourse(CourseDTO courseDTO) {
        Course course = convertCourseDTOtoCourse(courseDTO);
       try{
          return courseRepository.save(course);
       }catch(Exception e){
           e.printStackTrace();
           return null;
       }
    }

    @Override
    public Course findById(long id) {
        return courseRepository.findById(id).orElseThrow(()->new NoSuchElementException("Course not found!"));
    }

    @Override
    public Course updateCourse(Long id, CourseDTO courseDTO) {
        Course oldCourse = findById(id);
        if(oldCourse == null){
            return  null;
        }else{
            Course newCourse = convertCourseDTOtoCourse(courseDTO);
            newCourse.setId(id);
            try{
              return courseRepository.save(newCourse);
            }catch(Exception e){
                e.printStackTrace();
                return null;
            }
        }
    }

    @Override
    public boolean deleteCourse(Long id) {
        Course oldcourse = findById(id);
        if(oldcourse == null){
            return false;
        }else{
            try{
                courseRepository.delete(oldcourse);
                return true;
            }catch(Exception e){
                e.printStackTrace();
                return false;
            }
        }
    }

    @Override
    public Page<Course> getAllCourse(Pageable pageable) {
        return courseRepository.findAll(pageable);
    }
    public Course convertCourseDTOtoCourse(CourseDTO courseDTO) {
        return Course
                .builder()
                .courseName(courseDTO.getCourseName())
                .duration(courseDTO.getDuration())
                .description(courseDTO.getDescription())
                .build();
    }

}
