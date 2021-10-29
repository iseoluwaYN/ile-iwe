package com.ileiwe.ileiwe.service.instructor;

import com.ileiwe.ileiwe.data.dto.InstructorPartyDto;
import com.ileiwe.ileiwe.data.model.Authority;
import com.ileiwe.ileiwe.data.model.Instructor;
import com.ileiwe.ileiwe.data.model.LearningParty;
import com.ileiwe.ileiwe.data.model.Role;
import com.ileiwe.ileiwe.data.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstructorServiceImpl implements InstructorService{

    @Autowired
    InstructorRepository instructorRepository;

    @Override
    public Instructor save(InstructorPartyDto instructorDto) {
        if(instructorDto == null){
            throw new IllegalArgumentException("Instructor ca");
        }
        LearningParty learningParty = new LearningParty(instructorDto.getEmail(),
                instructorDto.getPassword(),
                new Authority(Role.ROLE_INSTRUCTOR));
        Instructor instructor = Instructor.builder()
                .lastName(instructorDto.getLastName()).firstName(instructorDto.getFirstName())
                .learningParty(learningParty).build();

        instructorRepository.save(instructor);
        return null;
    }
}
