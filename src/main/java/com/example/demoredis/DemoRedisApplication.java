package com.example.demoredis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@SpringBootApplication
@EnableCaching
@EnableCircuitBreaker
public class DemoRedisApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DemoRedisApplication.class, args);
    }

    @Autowired
    private SomeRepository someRepository;

    @Override
    public void run(String... args) throws Exception {
        long start = System.currentTimeMillis();
        System.out.println("Test");
        while (true) {
            System.out.printf("Time: %d%n", System.currentTimeMillis());
            System.out.printf("First try: %s%n", someRepository.get(5));
            System.out.printf("Second try: %s%n", someRepository.get(5));
            System.out.println("----------");
            Thread.sleep(1000);
        }
    }

}
