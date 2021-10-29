package com.ileiwe.ileiwe.service.instructor;

import com.ileiwe.ileiwe.data.dto.InstructorPartyDto;
import com.ileiwe.ileiwe.data.model.Instructor;

public interface InstructorService {
    Instructor save(InstructorPartyDto instructorDto);
}
