package com.quizgame;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import com.quizgame.player.PlayerManagementService;

public class MainApp {
    private Server server;
    private static final int PORT = 50051;

    public void start() throws Exception {
        try {
            PlayerManagementService playerService = new PlayerManagementService();
            server = ServerBuilder.forPort(PORT)
                    .addService(playerService)
                    .build();

            server.start();
            System.out.println("Server started successfully on port " + PORT);

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                System.out.println("Shutting down gRPC server");
                try {
                    if (server != null) {
                        server.shutdown().awaitTermination();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace(System.err);
                }
                System.out.println("Server shut down completed");
            }));
        } catch (Exception e) {
            System.err.println("Failed to start server on port " + PORT);
            throw e;
        }
    }

    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            System.out.println("Server is now accepting requests...");
            server.awaitTermination();
        }
    }

    public static void main(String[] args) {
        MainApp mainApp = new MainApp();
        try {
            mainApp.start();
            mainApp.blockUntilShutdown();
        } catch (Exception e) {
            System.err.println("Server failed to start: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}