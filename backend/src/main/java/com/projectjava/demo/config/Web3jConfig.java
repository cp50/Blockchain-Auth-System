package com.projectjava.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

@Configuration
public class Web3jConfig {

    private static final String INFURA_URL = "http://127.0.0.1:8545"; // Replace with your Infura URL or local blockchain RPC URL

    @Bean
    public Web3j web3j() {
        return Web3j.build(new HttpService(INFURA_URL));
    }
}
