package com.ozgur.giys.api.security;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtils jwtUtils;

    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);


    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        // if (this.userRepository.existsByUsername(loginRequest.getUsername())) {
        //     User user = this.userRepository.findByUsername(loginRequest.getUsername()).get();
        //     user.setIsLocked(false);
        //     this.userRepository.save(user);
        // }


        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        logger.info((String) authentication.getDetails());

        String jwt = this.jwtUtils.generateJwtToken(authentication);

        return ResponseEntity.ok() 
                .body(JwtResponse.builder()
                            .token(jwt)
                            .username(loginRequest.getUsername())
                            .type("Bearer")
                            .build());
    }


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
        
        if (this.userRepository.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity.badRequest().body(RegisterResponseMessage.builder().message("Username is already taken").build());
        }

        if (this.userRepository.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity.badRequest().body(RegisterResponseMessage.builder().message("Email is already in use").build());
        }

        User user = User.builder()
                        .username(signupRequest.getUsername())
                        .email(signupRequest.getEmail())
                        .password(this.encoder.encode(signupRequest.getPassword()))
                        .isLocked(false)
                        .build();
        this.userRepository.save(user);
        
        return ResponseEntity.ok().body(RegisterResponseMessage.builder().message("User created successfully").build());

    }

    // @PostMapping("/logout")
    // public ResponseEntity<?> logout(@RequestBody LoginRequest userRequest) {
        
    //     System.out.println(this.userRepository.existsByUsername(userRequest.getUsername()));

    //     if (this.userRepository.existsByUsername(userRequest.getUsername())) {
    //         logger.info("User found");
    //         User user = this.userRepository.findByUsername(userRequest.getUsername()).get();
    //         user.setIsLocked(true);
    //         this.userRepository.save(user);
    //         return ResponseEntity.ok().body("Logout successfully");
    //     } else {
    //         logger.error(userRequest.getUsername() + " User not found");
    //         return ResponseEntity.badRequest().body("User not found");

    //     }
    // }

}
