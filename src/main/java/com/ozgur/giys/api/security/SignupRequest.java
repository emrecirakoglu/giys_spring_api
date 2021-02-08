package com.ozgur.giys.api.security;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class SignupRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String email;
}
