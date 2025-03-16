package com.quizgame.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.quizgame.proto.GameplayGrpc;
import com.quizgame.proto.QuizRequest;
import com.quizgame.proto.QuizResponse;
import com.quizgame.proto.AnswerRequest;
import com.quizgame.proto.AnswerResponse;
import com.quizgame.rest.dto.AnswerDto;
import com.quizgame.rest.dto.RestApiResponse;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/game")
@Tag(name = "Game Play", description = "Operations for quiz gameplay")
public class GameController {

    private final GameplayGrpc.GameplayBlockingStub gameplayStub;

    @Autowired
    public GameController(GameplayGrpc.GameplayBlockingStub gameplayStub) {
        this.gameplayStub = gameplayStub;
    }

    @Operation(
            summary = "Load quiz questions",
            description = "Retrieves the questions for a quiz"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Quiz loaded successfully",
                    content = @Content(schema = @Schema(implementation = RestApiResponse.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error loading quiz",
                    content = @Content(schema = @Schema(implementation = RestApiResponse.class))
            )
    })
    @GetMapping("/quiz")
    public RestApiResponse<List<String>> loadQuiz() {
        try {
            // Create gRPC request
            QuizRequest request = QuizRequest.newBuilder().build();

            // Call gRPC service
            QuizResponse response = gameplayStub.loadQuiz(request);

            // Convert gRPC response to REST response
            return RestApiResponse.success("Quiz loaded successfully", response.getQuestionsList());
        } catch (Exception e) {
            return RestApiResponse.error("Failed to load quiz: " + e.getMessage());
        }
    }

    @Operation(
            summary = "Submit answer",
            description = "Submit a player's answer to a quiz question"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Answer submitted successfully",
                    content = @Content(schema = @Schema(implementation = RestApiResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid answer data",
                    content = @Content(schema = @Schema(implementation = RestApiResponse.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error submitting answer",
                    content = @Content(schema = @Schema(implementation = RestApiResponse.class))
            )
    })
    @PostMapping("/submit-answer")
    public RestApiResponse<String> submitAnswer(@Valid @RequestBody AnswerDto answerDto) {
        try {
            // Convert REST request to gRPC request
            AnswerRequest request = AnswerRequest.newBuilder()
                    .setPlayerName(answerDto.getPlayerName())
                    .setAnswer(answerDto.getAnswer())
                    .build();

            // Call gRPC service
            AnswerResponse response = gameplayStub.submitAnswer(request);

            // Convert gRPC response to REST response
            return RestApiResponse.success(response.getMessage());
        } catch (Exception e) {
            return RestApiResponse.error("Failed to submit answer: " + e.getMessage());
        }
    }
} 