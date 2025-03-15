package com.quizgame.database;

import com.mongodb.client.MongoCollection;
import com.quizgame.model.Player;
import org.bson.Document;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class PlayerDAOTest {
    private PlayerDAO playerDAO;
    private MongoCollection<Document> mockCollection;

    @Before
    public void setUp() {
        mockCollection = mock(MongoCollection.class);
        playerDAO = new PlayerDAO();
        playerDAO.setPlayerCollection(mockCollection);
    }

    @Test
    public void testInsertPlayer() {
        Player player = new Player("JohnDoe", 0, System.currentTimeMillis());
        playerDAO.insertPlayer(player);
        verify(mockCollection).insertOne(any(Document.class));
    }

    @Test
    public void testUpdateScore() {
        playerDAO.updateScore("JohnDoe", 100);
        verify(mockCollection).updateOne(any(Document.class), any(Document.class));
    }
}