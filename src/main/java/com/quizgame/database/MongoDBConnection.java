package com.quizgame.database;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

@Component
public class MongoDBConnection {
    private static MongoClient mongoClient;
    private static MongoDatabase database;

    static {
        String host = System.getenv("SPRING_DATA_MONGODB_HOST") != null ? 
                      System.getenv("SPRING_DATA_MONGODB_HOST") : "localhost";
        String port = System.getenv("SPRING_DATA_MONGODB_PORT") != null ? 
                      System.getenv("SPRING_DATA_MONGODB_PORT") : "27017";
        String username = System.getenv("SPRING_DATA_MONGODB_USERNAME") != null ? 
                          System.getenv("SPRING_DATA_MONGODB_USERNAME") : "root";
        String password = System.getenv("SPRING_DATA_MONGODB_PASSWORD") != null ? 
                          System.getenv("SPRING_DATA_MONGODB_PASSWORD") : "example";
        String dbName = System.getenv("SPRING_DATA_MONGODB_DATABASE") != null ? 
                        System.getenv("SPRING_DATA_MONGODB_DATABASE") : "QuizGame";
        
        String connectionString = "mongodb://" + username + ":" + password + "@" + host + ":" + port;
        
        mongoClient = MongoClients.create(connectionString);
        database = mongoClient.getDatabase(dbName);
        System.out.println("Connected to MongoDB: " + host + ":" + port + " | Database: " + dbName);
    }

    public static MongoDatabase getDatabase() {
        return database;
    }

    @PreDestroy
    public static void close() {
        if (mongoClient != null) {
            mongoClient.close();
            System.out.println("MongoDB connection closed");
        }
    }

    public static void addQuiz(String title, String description, List<Document> questions) {
        MongoCollection<Document> quizCollection = database.getCollection("Quizzes");
        Document quizDoc = new Document("title", title)
                .append("description", description)
                .append("questions", questions);
        quizCollection.insertOne(quizDoc);
    }
}