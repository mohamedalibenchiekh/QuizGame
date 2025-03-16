package com.quizgame.model;

import io.swagger.v3.oas.annotations.media.Schema;
import org.bson.types.ObjectId;
import java.util.ArrayList;
import java.util.List;

@Schema(description = "Quiz entity containing questions and metadata")
public class Quiz {
    @Schema(description = "Unique identifier for the quiz", accessMode = Schema.AccessMode.READ_ONLY)
    private ObjectId id;
    
    @Schema(description = "Quiz title", example = "Geography Quiz")
    private String title;
    
    @Schema(description = "Quiz description", example = "Test your knowledge of world geography")
    private String description;
    
    @Schema(description = "List of questions in this quiz")
    private List<Question> questions;
    
    public Quiz() {
        this.questions = new ArrayList<>();
    }
    
    public Quiz(String title, String description) {
        this.title = title;
        this.description = description;
        this.questions = new ArrayList<>();
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
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
    
    public void addQuestion(Question question) {
        this.questions.add(question);
    }
}