package com.quizgame.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class KafkaConsumerService {
    private KafkaConsumer<String, String> consumer;

    void setConsumer(KafkaConsumer<String, String> consumer) {
        this.consumer = consumer;
    }
    public KafkaConsumerService(String topic) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "quizgame-group");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        this.consumer = new KafkaConsumer<>(props);
        this.consumer.subscribe(Collections.singletonList(topic));
    }

    public void consumeMessages() {
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                System.out.println("Received message: " + record.value() + " from topic: " + record.topic());
                // Add logic to process the message (e.g., update scores in MongoDB)
            }
        }
    }

    public void close() {
        consumer.close();
    }
}