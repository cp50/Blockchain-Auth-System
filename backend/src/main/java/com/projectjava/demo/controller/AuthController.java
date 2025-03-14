package com.projectjava.demo.controller;

import com.projectjava.demo.model.User;
import com.projectjava.demo.repository.UserRepository;
import com.projectjava.demo.security.jwt.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(
            AuthenticationManager authenticationManager,
            JwtUtil jwtUtil,
            UserDetailsService userDetailsService,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // ✅ Test API to check if the service is running
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("API is working!");
    }

    // ✅ User Registration Endpoint
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String password = request.get("password");

        if (userRepository.findByEmail(email).isPresent()) {
            return ResponseEntity.badRequest().body("User already exists!");
        }

        // ✅ Corrected constructor usage
        User newUser = new User(email, passwordEncoder.encode(password));
        userRepository.save(newUser);

        return ResponseEntity.ok("User registered successfully!");
    }

    // ✅ User Login Endpoint with JWT token generation
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body("Invalid email or password");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        String token = jwtUtil.generateToken(userDetails.getUsername());

        return ResponseEntity.ok(Map.of("token", token));
    }
}
