package com.quizgame.rest.dto;

import javax.validation.constraints.NotBlank;

public class AnswerDto {

    @NotBlank(message = "Player name is required")
    private String playerName;

    @NotBlank(message = "Answer is required")
    private String answer;

    // Getters and setters
    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
} 