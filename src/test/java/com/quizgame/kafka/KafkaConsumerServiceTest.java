package com.quizgame.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import java.time.Duration;
import java.util.Collections;

public class KafkaConsumerServiceTest {
    private KafkaConsumer<String, String> mockConsumer;
    private KafkaConsumerService kafkaConsumerService;

    @Before
    public void setUp() {
        mockConsumer = mock(KafkaConsumer.class);
        kafkaConsumerService = new KafkaConsumerService("test-topic");
        kafkaConsumerService.setConsumer(mockConsumer);
    }

    @Test
    public void testConsumeMessages() {
        ConsumerRecord<String, String> record = new ConsumerRecord<>("test-topic", 0, 0, "key", "value");
        ConsumerRecords<String, String> records = new ConsumerRecords<>(Collections.singletonMap(new org.apache.kafka.common.TopicPartition("test-topic", 0), Collections.singletonList(record)));

        when(mockConsumer.poll(any(Duration.class))).thenReturn(records);

        kafkaConsumerService.consumeMessages();
        verify(mockConsumer).poll(any(Duration.class));
    }
}