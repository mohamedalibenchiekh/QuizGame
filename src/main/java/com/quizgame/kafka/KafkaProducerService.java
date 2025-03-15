package com.quizgame.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import java.util.Properties;

public class KafkaProducerService {
    private KafkaProducer<String, String> producer;

    void setProducer(KafkaProducer<String, String> producer) {
        this.producer = producer;
    }

    public KafkaProducerService() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        this.producer = new KafkaProducer<>(props);
    }

    public void sendMessage(String topic, String key, String value) {
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, value);
        producer.send(record);
        System.out.println("Sent message: " + value + " to topic: " + topic);
    }

    public void close() {
        producer.close();
    }
}