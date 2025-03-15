package com.quizgame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.ConfigurableApplicationContext;
import com.quizgame.rest.RestApiGatewayApplication;

import java.util.concurrent.CountDownLatch;

/**
 * Main application entry point that starts both the gRPC server 
 * and the Spring Boot REST API Gateway.
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.quizgame", "com.quizgame.rest"})
public class Application {
    
    public static void main(String[] args) {
        try {
            // Start Spring Boot REST API in a separate thread
            Thread restApiThread = new Thread(() -> {
                ConfigurableApplicationContext springContext = 
                    SpringApplication.run(RestApiGatewayApplication.class, args);
                
                // Register shutdown hook for clean shutdown
                Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                    System.out.println("Shutting down Spring Boot REST API...");
                    springContext.close();
                }));
            });
            restApiThread.start();
            
            // Start gRPC server
            MainApp grpcApp = new MainApp();
            grpcApp.start();
            
            // Keep the application running
            CountDownLatch latch = new CountDownLatch(1);
            Runtime.getRuntime().addShutdownHook(new Thread(() -> latch.countDown()));
            latch.await();
            
        } catch (Exception e) {
            System.err.println("Failed to start application: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
} 