package com.ileiwe.ileiwe.data.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LearningParty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
     @NotNull @NotBlank
    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    @NotNull @NotBlank
    private String email;

    private Boolean enabled;

    @CreationTimestamp   //persists the date and time the entity was created in the db
    private LocalDateTime dateCreated;
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Authority> authorities;

    public LearningParty(String email, String password, Authority authorities) {
//        if(email.strip().isEmpty() || password.strip().isEmpty()){
//            throw new IllegalArgumentException("email and password is empty");
//        }
        this.password = password;
        this.email = email;
        addAuthority(authorities);
        this.enabled = false;
    }

    public void addAuthority(Authority authority){

        if(this.authorities == null){
            this.authorities = new ArrayList<>();
        }
        this.authorities.add(authority);
    }
}
