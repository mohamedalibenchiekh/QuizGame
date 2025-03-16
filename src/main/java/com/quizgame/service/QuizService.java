package com.quizgame.service;

import com.quizgame.database.QuizDAO;
import com.quizgame.model.Question;
import com.quizgame.model.Quiz;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {
    private final QuizDAO quizDAO;
    
    @Autowired
    public QuizService(QuizDAO quizDAO) {
        this.quizDAO = quizDAO;
    }
    
    public Quiz createQuiz(String title, String description) {
        Quiz quiz = new Quiz(title, description);
        quizDAO.insertQuiz(quiz);
        return quiz;
    }
    
    public Quiz addQuizWithQuestions(String title, String description, List<Question> questions) {
        Quiz quiz = new Quiz(title, description);
        quiz.setQuestions(questions);
        quizDAO.insertQuiz(quiz);
        return quiz;
    }
    
    public void addQuestionToQuiz(ObjectId quizId, Question question) {
        Quiz quiz = quizDAO.getQuizById(quizId);
        if (quiz != null) {
            quiz.addQuestion(question);
            quizDAO.insertQuiz(quiz); // This will essentially update by replacing the document
        }
    }
    
    public Quiz getQuizById(String id) {
        return quizDAO.getQuizById(new ObjectId(id));
    }
    
    public List<Quiz> getAllQuizzes() {
        return quizDAO.getAllQuizzes();
    }
} 