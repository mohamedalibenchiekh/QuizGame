package com.quizgame.examples;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Simple example showing how to add a quiz directly to MongoDB
 */
public class AddQuizExample {
    
    public static void main(String[] args) {
        // MongoDB connection string with authentication
        String connectionString = "mongodb://root:example@localhost:27017";
        
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            // Get database
            MongoDatabase database = mongoClient.getDatabase("QuizGame");
            
            // Get collection
            MongoCollection<Document> quizCollection = database.getCollection("Quizzes");
            
            // Create questions
            List<Document> questions = new ArrayList<>();
            
            // Question 1
            Document question1 = new Document("text", "What is the capital of France?")
                    .append("options", Arrays.asList("Paris", "London", "Berlin", "Madrid"))
                    .append("correct_answer", 0);
            questions.add(question1);
            
            // Question 2
            Document question2 = new Document("text", "Which planet is known as the Red Planet?")
                    .append("options", Arrays.asList("Venus", "Mars", "Jupiter", "Mercury"))
                    .append("correct_answer", 1);
            questions.add(question2);
            
            // Question 3
            Document question3 = new Document("text", "What is the largest ocean on Earth?")
                    .append("options", Arrays.asList("Atlantic Ocean", "Indian Ocean", "Arctic Ocean", "Pacific Ocean"))
                    .append("correct_answer", 3);
            questions.add(question3);
            
            // Create quiz document
            Document quizDoc = new Document("title", "Geography Quiz")
                    .append("description", "Test your knowledge of world geography")
                    .append("questions", questions);
            
            // Insert quiz
            quizCollection.insertOne(quizDoc);
            
            System.out.println("Quiz inserted successfully!");
            System.out.println("Quiz contains " + questions.size() + " questions");
            
            // Print all quizzes in the database
            System.out.println("\nAll quizzes in the database:");
            quizCollection.find().forEach(doc -> {
                System.out.println("- " + doc.getString("title") + ": " + doc.getString("description"));
                List<Document> questionDocs = (List<Document>) doc.get("questions");
                System.out.println("  Contains " + questionDocs.size() + " questions");
            });
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 