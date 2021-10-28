package com.ileiwe.ileiwe.data.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class LearningParty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    private Boolean enabled;

    @CreationTimestamp   //persists the date and time the entity was created in the db
    private LocalDateTime dateCreated;
    @OneToMany
    private List<Authority> authorities;
}
