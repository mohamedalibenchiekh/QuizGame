package com.quizgame.database;

import com.mongodb.client.MongoCollection;
import com.quizgame.model.Quiz;
import org.bson.Document;

public class QuizDAO {
    private MongoCollection<Document> quizCollection;

    public QuizDAO() {
        this.quizCollection = MongoDBConnection.getDatabase().getCollection("Quizzes");
    }

    public void insertQuiz(Quiz quiz) {
        Document doc = new Document("question", quiz.getQuestion())
                .append("answers", quiz.getAnswers())
                .append("correct_answer", quiz.getCorrectAnswer());
        quizCollection.insertOne(doc);
        System.out.println("Quiz inserted: " + quiz.getQuestion());
    }

    void setQuizCollection(MongoCollection<Document> collection) {
        this.quizCollection = collection;
    }
}