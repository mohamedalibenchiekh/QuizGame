package com.quizgame.player;

import io.grpc.stub.StreamObserver;
import com.quizgame.proto.PlayerManagementServiceGrpc;
import com.quizgame.proto.RegisterPlayerRequest;
import com.quizgame.proto.RegisterPlayerResponse;

public class PlayerManagementService extends PlayerManagementServiceGrpc.PlayerManagementServiceImplBase {
    @Override
    public void registerPlayer(RegisterPlayerRequest request, StreamObserver<RegisterPlayerResponse> responseObserver) {
        System.out.println("Received registration request for user: " + request.getUsername());

        RegisterPlayerResponse response = RegisterPlayerResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Player " + request.getUsername() + " registered successfully!")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}