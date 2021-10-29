package com.ileiwe.ileiwe.data.repository;

import com.ileiwe.ileiwe.data.model.Authority;
import com.ileiwe.ileiwe.data.model.LearningParty;
import com.ileiwe.ileiwe.data.model.Role;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.useRepresentation;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@Transactional
@Sql(scripts = {"/db/insert.sql"})
class LearningPartyRepositoryTest {

    @Autowired
    LearningPartyRepository learningPartyRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void createdLearningPartyTest(){
        LearningParty learningUser = new LearningParty("my@gmail.com"
                , "mine123",
                new Authority(Role.ROLE_STUDENT));
        learningPartyRepository.save(learningUser);
        assertThat(learningUser.getId()).isNotNull();
        assertThat(learningUser.getEmail()).isEqualTo("my@gmail.com");
        assertThat(learningUser.getAuthorities()
                .get(0).getAuthority()).isEqualTo(Role.ROLE_STUDENT);

        log.info("After saving ->{}", learningUser);
    }

    @Test
    void createdLearningPartyUniqueEmailsTest(){
//        create a learning party
        LearningParty user1 = new LearningParty("my@gmail.com"
                , "mine123",
                new Authority(Role.ROLE_STUDENT));
//        save to db
        learningPartyRepository.save(user1);
        assertThat(user1.getId()).isNotNull();
        assertThat(user1.getEmail()).isEqualTo("my@gmail.com");
//        create another learning party with some email
        LearningParty user2 = new LearningParty("my@gmail.com"
                , "mine123",
                new Authority(Role.ROLE_STUDENT));
//        save and catch exception
        assertThrows(DataIntegrityViolationException.class,() -> learningPartyRepository.save(user2));
//        assert that exception was thrown

    }

    @Test
    void LearningPartyWithNullEmail(){
//         create a learning party with null values
        LearningParty user2 = new LearningParty(null,
                null,
                new Authority(Role.ROLE_STUDENT));
//          save and catch exception
        assertThrows(ConstraintViolationException.class, ()-> learningPartyRepository.save(user2));
    }

    @Test
    void LearningPartyWithEmptyStringValue(){
//         create a learning party with null values
        assertThrows(ConstraintViolationException.class,() -> new LearningParty("",
                "",
                new Authority(Role.ROLE_STUDENT)));
    }

    @Test
    void findUserNameTest(){
        LearningParty learningParty =
                learningPartyRepository.findByEmail("iseA@gmail.com");
        assertThat(learningParty).isNotNull();
        assertThat(learningParty.getEmail()).isEqualTo("iseA@gmail.com");
        log.info("Learning party object ->{}", learningParty);
    }

    @AfterEach
    void tearDown() {
    }
}