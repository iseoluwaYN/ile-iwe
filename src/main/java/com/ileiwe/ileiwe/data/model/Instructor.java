package com.ileiwe.ileiwe.data.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull @NotBlank
    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String specialization;

    @Column(length = 1000)
    private String bio;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn
    private LearningParty learningParty;

    @OneToMany
    private List<Course> courses;
}
