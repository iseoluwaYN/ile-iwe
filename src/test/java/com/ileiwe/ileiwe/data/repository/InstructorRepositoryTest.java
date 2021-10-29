package com.ileiwe.ileiwe.data.repository;

import com.ileiwe.ileiwe.data.model.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@Sql( scripts = {"/db/insert.sql"})
class InstructorRepositoryTest {


    @Autowired
    InstructorRepository instructorRepository;



    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void saveInstructorAsLearningPartyTest(){
//        create a learning party
        LearningParty user = new LearningParty("trainer@ileiwe.com"
                , "password", new Authority(Role.ROLE_INSTRUCTOR));
//        create instructor
        Instructor instructor = Instructor.builder()
                .firstName("John").lastName("Doe")
                .learningParty(user).build();
//        map instruc
        log.info("Before saving ->{}", instructor);
        instructorRepository.save(instructor);
        assertThat(instructor.getId()).isNotNull();
        assertThat(instructor.getLearningParty().getId()).isNotNull();
        log.info("After saving ->{}", instructor);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void updateInstructorTableAfterCreate(){
//        create instructor

        LearningParty user = new LearningParty("trainer@ileiwe.com"
                , "password", new Authority(Role.ROLE_INSTRUCTOR));
//        create instructor
        Instructor instructor = Instructor.builder()
                .firstName("John").lastName("Doe")
                .learningParty(user).build();
//        map instructor
        log.info("Before saving ->{}", instructor);

        instructorRepository.save(instructor);
        assertThat(instructor.getId()).isNotNull();
        assertThat(instructor.getLearningParty().getId()).isNotNull();
        log.info("After saving ->{}", instructor);

        Instructor savedInstructor=
            instructorRepository.findById(instructor.getId()).orElse(null);

        assertThat(savedInstructor).isNotNull();
        assertThat(savedInstructor.getBio()).isNull();
        assertThat(savedInstructor.getGender()).isNull();

        savedInstructor.setBio("I am a java lord");
        savedInstructor.setGender(Gender.FEMALE);

        instructorRepository.save(savedInstructor);
        assertThat(savedInstructor.getBio()).isNotNull();
        assertThat(savedInstructor.getGender()).isNotNull();
    }

    @Test
    void createInstructorWithNullValueTest(){
//        create a learning party
        LearningParty user = new LearningParty("trainer@ileiwe.com"
                , "password", new Authority(Role.ROLE_INSTRUCTOR));
//        create instructor with null values
        Instructor instructor = Instructor.builder()
                .firstName(null).lastName(null)
                .learningParty(user).build();

        assertThrows(ConstraintViolationException.class,() -> instructorRepository.save(instructor));
    }

    @Test
    void createInstructorWithEmptyStringValueTest(){
//        create a learning party
        LearningParty user = new LearningParty("trainer@ileiwe.com"
                , "password", new Authority(Role.ROLE_INSTRUCTOR));
//        create instructor with null values
        Instructor instructor = Instructor.builder()
                .firstName(" ").lastName(" ")
                .learningParty(user).build();

        assertThrows(ConstraintViolationException.class,() -> instructorRepository.save(instructor));
    }

}