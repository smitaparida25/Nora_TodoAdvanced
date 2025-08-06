package com.nora.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TasklistApplication {
    public static void main(String[] args) {
        SpringApplication.run(TasklistApplication.class, args);
    }
}
