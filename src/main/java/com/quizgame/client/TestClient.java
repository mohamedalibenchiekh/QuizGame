package com.quizgame.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import com.quizgame.proto.PlayerManagementServiceGrpc;
import com.quizgame.proto.RegisterPlayerRequest;
import com.quizgame.proto.RegisterPlayerResponse;

public class TestClient {
    private final ManagedChannel channel;
    private final PlayerManagementServiceGrpc.PlayerManagementServiceBlockingStub stub;

    public TestClient(String host, int port) {
        // Create a channel
        channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();

        // Create a blocking stub
        stub = PlayerManagementServiceGrpc.newBlockingStub(channel);
    }

    public void testRegisterPlayer() {
        try {
            System.out.println("Attempting to register a new player...");

            // Create registration request
            RegisterPlayerRequest request = RegisterPlayerRequest.newBuilder()
                    .setUsername("testUser")
                    .setPassword("testPass")
                    .build();

            // Send request and get response
            RegisterPlayerResponse response = stub.registerPlayer(request);

            System.out.println("Server Response: " + response.getMessage());
        } catch (Exception e) {
            System.err.println("RPC failed: " + e.getMessage());
        } finally {
            shutdown();
        }
    }

    public void shutdown() {
        if (channel != null && !channel.isShutdown()) {
            channel.shutdown();
        }
    }

    public static void main(String[] args) {
        String host = "localhost";
        int port = 50051;
        
        // Allow overriding the host from command line
        if (args.length > 0) {
            host = args[0];
        }
        
        // Allow overriding the port from command line
        if (args.length > 1) {
            try {
                port = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                System.err.println("Invalid port number: " + args[1] + ". Using default port 50051.");
            }
        }
        
        System.out.println("Connecting to server at " + host + ":" + port);
        TestClient client = new TestClient(host, port);
        client.testRegisterPlayer();
    }
}