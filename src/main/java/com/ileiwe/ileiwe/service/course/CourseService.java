package com.ileiwe.ileiwe.service.instructor;

import com.ileiwe.ileiwe.data.dto.CourseDto;
import com.ileiwe.ileiwe.data.model.Course;

public interface CourseService {
    void create(CourseDto courseDto);
    Course update(CourseDto courseDto);
    Course publish();
    void remove(Course id);
    Course getCourse(Course course);
}
