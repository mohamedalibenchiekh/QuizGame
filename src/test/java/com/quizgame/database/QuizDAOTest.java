package com.quizgame.database;

import com.mongodb.client.MongoCollection;
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
        Quiz quiz = new Quiz("What is the capital of France?", Arrays.asList("Paris", "London", "Berlin"), 0);
        quizDAO.insertQuiz(quiz);
        verify(mockCollection).insertOne(any(Document.class));
    }
}