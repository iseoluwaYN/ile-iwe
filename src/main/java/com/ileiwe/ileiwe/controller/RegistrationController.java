package com.ileiwe.ileiwe.controller;

import com.ileiwe.ileiwe.data.dto.InstructorPartyDto;
import com.ileiwe.ileiwe.exception.UserAlreadyExistsException;
import com.ileiwe.ileiwe.service.instructor.InstructorServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api")
public class RegistrationController {
    @Autowired
    InstructorServiceImpl instructorService;

    public ResponseEntity<?> registerAsInstructor(@RequestBody InstructorPartyDto instructorPartyDto) {
        log.info("instructor object ->{}", instructorPartyDto);
        try {
            return ResponseEntity.ok()
                    .body(instructorService.save(instructorPartyDto));
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
