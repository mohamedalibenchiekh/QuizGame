package com.quizgame.rest.controller;

import com.quizgame.model.Question;
import com.quizgame.model.Quiz;
import com.quizgame.rest.dto.RestApiResponse;
import com.quizgame.rest.dto.QuizDto;
import com.quizgame.service.QuizService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
@Tag(name = "Quiz Management", description = "Operations for creating and managing quizzes")
public class QuizApiController {

    private final QuizService quizService;

    @Autowired
    public QuizApiController(QuizService quizService) {
        this.quizService = quizService;
    }

    @Operation(
            summary = "Get all quizzes",
            description = "Retrieves a list of all available quizzes"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved the list of quizzes",
                    content = @Content(schema = @Schema(implementation = Quiz.class))
            )
    })
    @GetMapping
    public ResponseEntity<RestApiResponse<List<Quiz>>> getAllQuizzes() {
        List<Quiz> quizzes = quizService.getAllQuizzes();
        return ResponseEntity.ok(RestApiResponse.success("Quizzes retrieved successfully", quizzes));
    }

    @Operation(
            summary = "Get quiz by ID",
            description = "Retrieves a specific quiz by its ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved the quiz",
                    content = @Content(schema = @Schema(implementation = Quiz.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Quiz not found"
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<RestApiResponse<Quiz>> getQuizById(
            @Parameter(description = "ID of the quiz to retrieve") @PathVariable String id) {
        try {
            Quiz quiz = quizService.getQuizById(id);
            if (quiz == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(RestApiResponse.error("Quiz not found with ID: " + id));
            }
            return ResponseEntity.ok(RestApiResponse.success("Quiz retrieved successfully", quiz));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(RestApiResponse.error("Error retrieving quiz: " + e.getMessage()));
        }
    }

    @Operation(
            summary = "Create a new quiz",
            description = "Creates a new quiz with the provided information"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Quiz created successfully",
                    content = @Content(schema = @Schema(implementation = Quiz.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid quiz data provided"
            )
    })
    @PostMapping
    public ResponseEntity<RestApiResponse<Quiz>> createQuiz(
            @Parameter(description = "Quiz object to be created") @Valid @RequestBody QuizDto quizDto) {
        try {
            Quiz quiz = quizService.addQuizWithQuestions(
                    quizDto.getTitle(),
                    quizDto.getDescription(),
                    quizDto.getQuestions()
            );
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(RestApiResponse.success("Quiz created successfully", quiz));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(RestApiResponse.error("Error creating quiz: " + e.getMessage()));
        }
    }

    @Operation(
            summary = "Add a question to a quiz",
            description = "Adds a new question to an existing quiz"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Question added successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Quiz not found"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid question data"
            )
    })
    @PostMapping("/{id}/questions")
    public ResponseEntity<RestApiResponse<String>> addQuestionToQuiz(
            @Parameter(description = "ID of the quiz to add the question to") @PathVariable String id,
            @Parameter(description = "Question to add") @Valid @RequestBody Question question) {
        try {
            quizService.addQuestionToQuiz(new ObjectId(id), question);
            return ResponseEntity.ok(
                    RestApiResponse.success("Question added successfully to quiz with ID: " + id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(RestApiResponse.error("Quiz not found with ID: " + id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(RestApiResponse.error("Error adding question: " + e.getMessage()));
        }
    }
} 