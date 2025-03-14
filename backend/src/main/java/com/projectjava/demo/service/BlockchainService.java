package com.projectjava.demo.service;

import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;

import java.io.IOException;

@Service
public class BlockchainService {

    private final Web3j web3j;

    public BlockchainService(Web3j web3j) {
        this.web3j = web3j;
    }

    public String getClientVersion() {
        try {
            Web3ClientVersion web3ClientVersion = web3j.web3ClientVersion().send();
            return web3ClientVersion.getWeb3ClientVersion();
        } catch (IOException e) {
            e.printStackTrace();
            return "Error fetching client version: " + e.getMessage();
        }
    }
}
