package com.quizgame.examples;

import com.quizgame.database.MongoDBConnection;
import com.quizgame.database.QuizDAO;
import com.quizgame.model.Question;
import com.quizgame.model.Quiz;
import com.quizgame.service.QuizService;

import java.util.Arrays;
import java.util.List;

public class QuizCreationExample {
    
    public static void main(String[] args) {
        try {
            // Create dependencies
            QuizDAO quizDAO = new QuizDAO();
            
            // Create a new quiz service with the DAO
            QuizService quizService = new QuizService(quizDAO);
            
            // Create a simple quiz
            Quiz quiz = quizService.createQuiz("Geography Quiz", "Test your knowledge of world geography");
            
            // Add questions to the quiz
            Question question1 = new Question(
                "What is the capital of France?",
                Arrays.asList("Paris", "London", "Berlin", "Madrid"),
                0 // Paris is the correct answer (index 0)
            );
            
            Question question2 = new Question(
                "Which planet is known as the Red Planet?",
                Arrays.asList("Venus", "Mars", "Jupiter", "Mercury"),
                1 // Mars is the correct answer (index 1)
            );
            
            Question question3 = new Question(
                "What is the largest ocean on Earth?",
                Arrays.asList("Atlantic Ocean", "Indian Ocean", "Arctic Ocean", "Pacific Ocean"),
                3 // Pacific Ocean is the correct answer (index 3)
            );
            
            // Add questions to the quiz
            quiz.addQuestion(question1);
            quiz.addQuestion(question2);
            quiz.addQuestion(question3);
            
            // Save the quiz to the database
            quizService.addQuizWithQuestions(quiz.getTitle(), quiz.getDescription(), quiz.getQuestions());
            
            System.out.println("Quiz created successfully with ID: " + quiz.getId());
            System.out.println("Quiz contains " + quiz.getQuestions().size() + " questions");
            
            // Retrieve all quizzes
            List<Quiz> allQuizzes = quizService.getAllQuizzes();
            System.out.println("Total quizzes in the database: " + allQuizzes.size());
            
            // Don't manually close MongoDB connection in example code
            // Spring will handle closing it with @PreDestroy
            
        } catch (Exception e) {
            System.err.println("Error creating quiz: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 