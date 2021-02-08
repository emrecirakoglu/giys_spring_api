package com.ozgur.giys.api.security;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtResponse {
    private String token;
    private String type;
    private String username;
}
