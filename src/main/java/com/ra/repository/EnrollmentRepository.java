package com.ra.repository;

import com.ra.model.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment,Long> {
    List<Enrollment> findByUserId(Long userId);
}
