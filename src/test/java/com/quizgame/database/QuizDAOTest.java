package com.quizgame.database;

import com.mongodb.client.MongoCollection;
import com.quizgame.model.Question;
import com.quizgame.model.Quiz;
import org.bson.Document;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import java.util.Arrays;

public class QuizDAOTest {
    private QuizDAO quizDAO;
    private MongoCollection<Document> mockCollection;

    @Before
    public void setUp() {
        mockCollection = mock(MongoCollection.class);
        quizDAO = new QuizDAO();
        quizDAO.setQuizCollection(mockCollection);
    }

    @Test
    public void testInsertQuiz() {
        // Create a quiz
        Quiz quiz = new Quiz("Geography Quiz", "Test your knowledge of world geography");
        
        // Create a question
        Question question = new Question("What is the capital of France?", 
                                         Arrays.asList("Paris", "London", "Berlin"), 
                                         0);
        quiz.addQuestion(question);
        
        // Insert the quiz
        quizDAO.insertQuiz(quiz);
        
        // Verify the mock was called
        verify(mockCollection).insertOne(any(Document.class));
    }
}