package com.ozgur.giys.api.security;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table( name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true)
    @NotBlank
    @Size(max = 20)
    private String username;

    @Column(name = "password")
    @NotBlank
    @Size(min = 6)
    private String password;

    @Column(name = "email", unique = true)
    @Email
    @NotBlank
    private String email;


    @Column(name = "is_locked")
    private Boolean isLocked;

}
