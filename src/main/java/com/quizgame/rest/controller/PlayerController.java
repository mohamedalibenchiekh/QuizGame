package com.quizgame.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quizgame.proto.PlayerManagementServiceGrpc;
import com.quizgame.proto.RegisterPlayerRequest;
import com.quizgame.proto.RegisterPlayerResponse;
import com.quizgame.rest.dto.ApiResponse;
import com.quizgame.rest.dto.PlayerDto;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    private final PlayerManagementServiceGrpc.PlayerManagementServiceBlockingStub playerManagementStub;

    @Autowired
    public PlayerController(PlayerManagementServiceGrpc.PlayerManagementServiceBlockingStub playerManagementStub) {
        this.playerManagementStub = playerManagementStub;
    }

    @PostMapping("/register")
    public ApiResponse<String> registerPlayer(@Valid @RequestBody PlayerDto playerDto) {
        try {
            // Convert REST request to gRPC request
            RegisterPlayerRequest grpcRequest = RegisterPlayerRequest.newBuilder()
                    .setUsername(playerDto.getUsername())
                    .setPassword(playerDto.getPassword())
                    .build();

            // Call gRPC service
            RegisterPlayerResponse grpcResponse = playerManagementStub.registerPlayer(grpcRequest);

            // Convert gRPC response to REST response
            return ApiResponse.success(grpcResponse.getMessage());
        } catch (Exception e) {
            return ApiResponse.error("Failed to register player: " + e.getMessage());
        }
    }
} 