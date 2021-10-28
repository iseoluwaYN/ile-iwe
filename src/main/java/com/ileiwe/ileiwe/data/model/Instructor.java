package com.ileiwe.ileiwe.data.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String specialization;

    @Column(length = 100)
    private String bio;

    @OneToOne
    @JoinColumn
    private LearningParty learningParty;

    @OneToMany
    private List<Course> courses;
}
