package com.quizgame.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class KafkaProducerServiceTest {
    private KafkaProducer<String, String> mockProducer;
    private KafkaProducerService kafkaProducerService;

    @Before
    public void setUp() {
        mockProducer = mock(KafkaProducer.class);
        kafkaProducerService = new KafkaProducerService();
        kafkaProducerService.setProducer(mockProducer);
    }

    @Test
    public void testSendMessage() {
        kafkaProducerService.sendMessage("test-topic", "key", "value");
        verify(mockProducer).send(any(ProducerRecord.class));
    }
}