package com.quizgame.rest.dto;

import com.quizgame.model.Question;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Schema(description = "Data transfer object for Quiz information")
public class QuizDto {

    @Schema(description = "Quiz title", example = "Geography Quiz", required = true)
    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
    private String title;

    @Schema(description = "Quiz description", example = "Test your knowledge of world geography", required = true)
    @NotBlank(message = "Description is required")
    @Size(min = 10, max = 500, message = "Description must be between 10 and 500 characters")
    private String description;

    @Schema(description = "List of questions in this quiz")
    @NotNull(message = "Quiz must have at least one question")
    @Size(min = 1, message = "Quiz must have at least one question")
    private List<Question> questions = new ArrayList<>();

    public QuizDto() {
    }

    public QuizDto(String title, String description, List<Question> questions) {
        this.title = title;
        this.description = description;
        this.questions = questions;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
} 