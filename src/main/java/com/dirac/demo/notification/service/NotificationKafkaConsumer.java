package com.dirac.demo.notification.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class NotificationKafkaConsumer {

    private final Logger log = LoggerFactory.getLogger(NotificationKafkaConsumer.class);
    private static final String TOPIC = "topic_notification";

    @KafkaListener(topics = "topic_notification", groupId = "group_id")
    public void consume(String message) throws IOException {
        log.info("Consumed message in {} : {}", TOPIC, message);
    }
}
