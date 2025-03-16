package com.quizgame.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import com.quizgame.proto.PlayerManagementServiceGrpc;
import com.quizgame.proto.GameplayGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@SpringBootApplication
@ComponentScan(basePackages = {"com.quizgame.rest", "com.quizgame.service", "com.quizgame.database"})
public class RestApiGatewayApplication {

    private static final String GRPC_SERVER_HOST = "localhost";
    private static final int GRPC_SERVER_PORT = 50051;

    public static void main(String[] args) {
        SpringApplication.run(RestApiGatewayApplication.class, args);
    }

    @Bean
    public ManagedChannel grpcChannel() {
        return ManagedChannelBuilder
                .forAddress(GRPC_SERVER_HOST, GRPC_SERVER_PORT)
                .usePlaintext()
                .build();
    }

    @Bean
    public PlayerManagementServiceGrpc.PlayerManagementServiceBlockingStub playerManagementStub(ManagedChannel channel) {
        return PlayerManagementServiceGrpc.newBlockingStub(channel);
    }

    @Bean
    public GameplayGrpc.GameplayBlockingStub gameplayStub(ManagedChannel channel) {
        return GameplayGrpc.newBlockingStub(channel);
    }
} 