package com.ozgur.giys.api.messaging;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RabbitmqUser {
    
    private String name;
    private String password_hash;
    private String hashing_algorithm;
    private String tags;

}
