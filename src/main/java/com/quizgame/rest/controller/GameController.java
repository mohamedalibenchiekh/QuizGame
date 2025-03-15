package com.quizgame.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.quizgame.proto.GameplayGrpc;
import com.quizgame.proto.QuizRequest;
import com.quizgame.proto.QuizResponse;
import com.quizgame.proto.AnswerRequest;
import com.quizgame.proto.AnswerResponse;
import com.quizgame.rest.dto.AnswerDto;
import com.quizgame.rest.dto.ApiResponse;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/game")
public class GameController {

    private final GameplayGrpc.GameplayBlockingStub gameplayStub;

    @Autowired
    public GameController(GameplayGrpc.GameplayBlockingStub gameplayStub) {
        this.gameplayStub = gameplayStub;
    }

    @GetMapping("/quiz")
    public ApiResponse<List<String>> loadQuiz() {
        try {
            // Create gRPC request
            QuizRequest request = QuizRequest.newBuilder().build();

            // Call gRPC service
            QuizResponse response = gameplayStub.loadQuiz(request);

            // Convert gRPC response to REST response
            return ApiResponse.success("Quiz loaded successfully", response.getQuestionsList());
        } catch (Exception e) {
            return ApiResponse.error("Failed to load quiz: " + e.getMessage());
        }
    }

    @PostMapping("/submit-answer")
    public ApiResponse<String> submitAnswer(@Valid @RequestBody AnswerDto answerDto) {
        try {
            // Convert REST request to gRPC request
            AnswerRequest request = AnswerRequest.newBuilder()
                    .setPlayerName(answerDto.getPlayerName())
                    .setAnswer(answerDto.getAnswer())
                    .build();

            // Call gRPC service
            AnswerResponse response = gameplayStub.submitAnswer(request);

            // Convert gRPC response to REST response
            return ApiResponse.success(response.getMessage());
        } catch (Exception e) {
            return ApiResponse.error("Failed to submit answer: " + e.getMessage());
        }
    }
} 