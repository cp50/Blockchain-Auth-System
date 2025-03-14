package com.projectjava.demo.service;

import com.projectjava.demo.model.User;
import com.projectjava.demo.repository.UserRepository;
import com.projectjava.demo.security.jwt.JwtUtil;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    // ✅ Constructor Injection (Fixes missing initialization issues)
    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           JwtUtil jwtUtil,
                           AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    // ✅ Register user with encrypted password
    @Transactional
    @Override
    public String registerUser(User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            return "Email already registered!";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword())); // Hash password
        userRepository.save(user);
        return "User registered successfully!";
    }

    // ✅ Use Spring Security for authentication
    @Override
    public String loginUser(User user) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
            );

            // ✅ Generate JWT token after successful authentication
            return jwtUtil.generateToken(user.getEmail());
        } catch (Exception e) {
            return "Invalid credentials!";
        }
    }
}
