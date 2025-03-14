package com.projectjava.demo.controller;

import com.projectjava.demo.service.BlockchainService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/blockchain")
public class BlockchainController {

    private final BlockchainService blockchainService;

    public BlockchainController(BlockchainService blockchainService) {
        this.blockchainService = blockchainService;
    }

    @GetMapping("/clientVersion")
    public String getClientVersion() {
        return blockchainService.getClientVersion();
    }
}
