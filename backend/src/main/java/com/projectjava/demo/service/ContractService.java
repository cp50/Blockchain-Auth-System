package com.projectjava.demo.service;

import com.projectjava.demo.Auth;
import com.projectjava.demo.security.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.StaticGasProvider;

import java.math.BigInteger;
import java.util.concurrent.CompletableFuture;

@Service
public class ContractService {

    private final Web3j web3j;
    private final Credentials credentials;
    private final ContractGasProvider gasProvider;
    private final String contractAddress;
    private final Auth authContract; // Smart Contract Instance
    private final JwtUtil jwtUtil; // JWT Utility for token generation

    public ContractService(Web3j web3j,
                           @Value("${blockchain.private-key}") String privateKey,
                           @Value("${blockchain.contract-address}") String contractAddress,
                           JwtUtil jwtUtil) {  // Inject JWT Utility
        this.web3j = web3j;
        this.credentials = Credentials.create(privateKey);
        this.contractAddress = contractAddress;
        this.gasProvider = new StaticGasProvider(BigInteger.valueOf(20000000000L), BigInteger.valueOf(6721975));
        this.authContract = Auth.load(contractAddress, web3j, credentials, gasProvider);
        this.jwtUtil = jwtUtil; // Initialize JWT Utility
    }

    // ✅ Method to register a user on the blockchain
    public CompletableFuture<TransactionReceipt> registerUser(String email, String password) {
        return authContract.register(email, password)
                .sendAsync();
    }

    // ✅ Method to authenticate a user from the blockchain and return JWT Token
    public ResponseEntity<?> authenticateUser(String email, String password) {
        try {
            CompletableFuture<Boolean> result = authContract.authenticate(email, password).sendAsync();
            boolean isAuthenticated = result.get(); // Wait for result

            if (isAuthenticated) {
                // ✅ Generate JWT Token
                String token = jwtUtil.generateToken(email); // Generate token with email as subject
                return ResponseEntity.ok(token);
            } else {
                return ResponseEntity.status(401).body("Authentication failed: Invalid credentials.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error during authentication: " + e.getMessage());
        }
    }

    public Auth getAuthContract() {
        return authContract;
    }
}
