package com.projectjava.demo.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class MongoDBConnectionTest implements CommandLineRunner {
    private final MongoTemplate mongoTemplate;

    public MongoDBConnectionTest(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void run(String... args) {
        try {
            System.out.println("✅ Connected to MongoDB: " + mongoTemplate.getDb().getName());
        } catch (Exception e) {
            System.out.println("❌ MongoDB Connection Failed: " + e.getMessage());
        }
    }
}
