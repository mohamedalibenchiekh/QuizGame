package com.quizgame.model;

public class Player {
    private String playerName;
    private int score;
    private long lastActive;

    public Player(String playerName, int score, long lastActive) {
        this.playerName = playerName;
        this.score = score;
        this.lastActive = lastActive;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getScore() {
        return score;
    }

    public long getLastActive() {
        return lastActive;
    }
}