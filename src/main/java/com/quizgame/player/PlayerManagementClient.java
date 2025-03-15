package com.quizgame.player;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import com.quizgame.proto.PlayerManagementServiceGrpc;
import com.quizgame.proto.RegisterPlayerRequest;
import com.quizgame.proto.RegisterPlayerResponse;

public class PlayerManagementClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        PlayerManagementServiceGrpc.PlayerManagementServiceBlockingStub stub =
                PlayerManagementServiceGrpc.newBlockingStub(channel);

        RegisterPlayerRequest request = RegisterPlayerRequest.newBuilder()
                .setUsername("JohnDoe")
                .setPassword("password123")
                .build();

        RegisterPlayerResponse response = stub.registerPlayer(request);
        System.out.println("Response from server: " + response.getMessage());

        channel.shutdown();
    }
}