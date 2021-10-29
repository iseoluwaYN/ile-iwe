package com.ileiwe.ileiwe.data.repository;

import com.ileiwe.ileiwe.data.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
