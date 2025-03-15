package com.quizgame.database;

import com.mongodb.client.MongoCollection;
import com.quizgame.database.MongoDBConnection;
import org.bson.Document;

public class TestDatabaseConnection {
    public static void main(String[] args) {
        MongoCollection<Document> players = MongoDBConnection.getDatabase().getCollection("Players");
        System.out.println("Number of players: " + players.countDocuments());
        MongoDBConnection.close();
    }
}