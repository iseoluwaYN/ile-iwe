package com.ileiwe.ileiwe.data.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Data
public class Course {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;

    @CreationTimestamp
    private LocalDateTime dateCreated;
    private LocalDateTime datePublished;

    @UpdateTimestamp
    private LocalDateTime updated;

    private String language;
    private String duration;
    private boolean isPublished;

    @ElementCollection
    private List<String> imageUrls;


    @ManyToOne
    private Instructor instructor;
    @ManyToMany
    private List<Student> students;

}
