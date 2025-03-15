package com.quizgame.database;

import com.mongodb.client.MongoCollection;
import com.quizgame.model.Player;
import org.bson.Document;

public class PlayerDAO {
    private MongoCollection<Document> playerCollection;

    public PlayerDAO() {
        this.playerCollection = MongoDBConnection.getDatabase().getCollection("Players");
    }

    public void insertPlayer(Player player) {
        Document doc = new Document("playerName", player.getPlayerName())
                .append("score", player.getScore())
                .append("last_active", player.getLastActive());
        playerCollection.insertOne(doc);
        System.out.println("Player inserted: " + player.getPlayerName());
    }

    public void updateScore(String playerName, int score) {
        Document query = new Document("playerName", playerName);
        Document update = new Document("$set", new Document("score", score));
        playerCollection.updateOne(query, update);
        System.out.println("Score updated for player: " + playerName);
    }

    void setPlayerCollection(MongoCollection<Document> collection) {
        this.playerCollection = collection;
    }
}