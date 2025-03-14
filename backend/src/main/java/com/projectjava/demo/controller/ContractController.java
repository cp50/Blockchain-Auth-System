package com.projectjava.demo.controller;

import com.projectjava.demo.service.ContractService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/blockchain")
public class ContractController {

    private final ContractService contractService;

    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    // ✅ Register User on Blockchain
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String password = request.get("password");

        if (email == null || password == null) {
            return ResponseEntity.badRequest().body("Missing email or password.");
        }

        try {
            CompletableFuture<?> transaction = contractService.registerUser(email, password);
            transaction.get(); // Wait for completion
            return ResponseEntity.ok("User registered successfully!");
        } catch (InterruptedException | ExecutionException e) {
            return ResponseEntity.badRequest().body("Registration failed: " + e.getMessage());
        }
    }

    // ✅ Authenticate User on Blockchain (Returns JWT Token)
    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticateUser(@RequestParam String email, @RequestParam String password) {
        return contractService.authenticateUser(email, password);
    }
}
