package com.quizgame.database;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.quizgame.model.Question;
import com.quizgame.model.Quiz;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class QuizDAO {
    private MongoCollection<Document> quizCollection;

    public QuizDAO() {
        this.quizCollection = MongoDBConnection.getDatabase().getCollection("Quizzes");
    }

    public ObjectId insertQuiz(Quiz quiz) {
        List<Document> questionDocs = new ArrayList<>();
        
        for (Question question : quiz.getQuestions()) {
            Document questionDoc = new Document("text", question.getText())
                    .append("options", question.getOptions())
                    .append("correct_answer", question.getCorrectAnswer());
            questionDocs.add(questionDoc);
        }
        
        Document doc = new Document("title", quiz.getTitle())
                .append("description", quiz.getDescription())
                .append("questions", questionDocs);
        
        quizCollection.insertOne(doc);
        ObjectId id = doc.getObjectId("_id");
        quiz.setId(id);
        
        System.out.println("Quiz inserted: " + quiz.getTitle() + " with ID: " + id);
        return id;
    }
    
    public Quiz getQuizById(ObjectId id) {
        Document doc = quizCollection.find(Filters.eq("_id", id)).first();
        return documentToQuiz(doc);
    }
    
    public List<Quiz> getAllQuizzes() {
        FindIterable<Document> documents = quizCollection.find();
        List<Quiz> quizzes = new ArrayList<>();
        
        for (Document doc : documents) {
            quizzes.add(documentToQuiz(doc));
        }
        
        return quizzes;
    }
    
    private Quiz documentToQuiz(Document doc) {
        if (doc == null) {
            return null;
        }
        
        Quiz quiz = new Quiz();
        quiz.setId(doc.getObjectId("_id"));
        quiz.setTitle(doc.getString("title"));
        quiz.setDescription(doc.getString("description"));
        
        List<Document> questionDocs = (List<Document>) doc.get("questions");
        List<Question> questions = questionDocs.stream().map(qDoc -> {
            Question question = new Question();
            question.setText(qDoc.getString("text"));
            question.setOptions((List<String>) qDoc.get("options"));
            question.setCorrectAnswer(qDoc.getInteger("correct_answer"));
            return question;
        }).collect(Collectors.toList());
        
        quiz.setQuestions(questions);
        return quiz;
    }

    void setQuizCollection(MongoCollection<Document> collection) {
        this.quizCollection = collection;
    }
}