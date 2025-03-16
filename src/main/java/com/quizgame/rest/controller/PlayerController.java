package com.quizgame.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quizgame.proto.PlayerManagementServiceGrpc;
import com.quizgame.proto.RegisterPlayerRequest;
import com.quizgame.proto.RegisterPlayerResponse;
import com.quizgame.rest.dto.RestApiResponse;
import com.quizgame.rest.dto.PlayerDto;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/players")
@Tag(name = "Player Management", description = "Operations for player registration and management")
public class PlayerController {

    private final PlayerManagementServiceGrpc.PlayerManagementServiceBlockingStub playerManagementStub;

    @Autowired
    public PlayerController(PlayerManagementServiceGrpc.PlayerManagementServiceBlockingStub playerManagementStub) {
        this.playerManagementStub = playerManagementStub;
    }

    @Operation(
            summary = "Register a new player",
            description = "Creates a new player account with the provided credentials"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Player registered successfully",
                    content = @Content(schema = @Schema(implementation = RestApiResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid player data or player already exists",
                    content = @Content(schema = @Schema(implementation = RestApiResponse.class))
            )
    })
    @PostMapping("/register")
    public RestApiResponse<String> registerPlayer(@Valid @RequestBody PlayerDto playerDto) {
        try {
            // Convert REST request to gRPC request
            RegisterPlayerRequest grpcRequest = RegisterPlayerRequest.newBuilder()
                    .setUsername(playerDto.getUsername())
                    .setPassword(playerDto.getPassword())
                    .build();

            // Call gRPC service
            RegisterPlayerResponse grpcResponse = playerManagementStub.registerPlayer(grpcRequest);

            // Convert gRPC response to REST response
            return RestApiResponse.success(grpcResponse.getMessage());
        } catch (Exception e) {
            return RestApiResponse.error("Failed to register player: " + e.getMessage());
        }
    }
} 