package com.quizgame.database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDBConnection {
    private static MongoClient mongoClient;
    private static MongoDatabase database;

    static {
        mongoClient = MongoClients.create("mongodb://localhost:27017");
        database = mongoClient.getDatabase("QuizGame");
    }

    public static MongoDatabase getDatabase() {
        return database;
    }

    public static void close() {
        mongoClient.close();
    }
}