package com.quizgame.model;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Question entity with multiple choice options")
public class Question {
    @Schema(description = "Question text", example = "What is the capital of France?")
    private String text;
    
    @Schema(description = "List of possible answers", example = "[\"Paris\", \"London\", \"Berlin\", \"Madrid\"]")
    private List<String> options;
    
    @Schema(description = "Index of the correct answer in the options list", example = "0")
    private int correctAnswer;

    public Question() {
    }

    public Question(String text, List<String> options, int correctAnswer) {
        this.text = text;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
} 