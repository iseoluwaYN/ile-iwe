package com.ileiwe.ileiwe.service.instructor;

import com.ileiwe.ileiwe.data.dto.CourseDto;
import com.ileiwe.ileiwe.data.model.Course;
import com.ileiwe.ileiwe.data.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CourseServiceImpl implements  CourseService{

    @Autowired
    CourseRepository courseRepository;

    @Override
    public void create(CourseDto courseDto) {

    }
;
    @Override
    public Course update(CourseDto courseDto) {
            return null;
    }

    @Override
    public Course publish() {
        return null;
    }

    @Override
    public void remove(Course id) {
            courseRepository.delete(id);
    }

    @Override
    public Course getCourse(Course id) {

        return courseRepository.getById(id);
    }
}
