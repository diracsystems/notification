package com.dirac.demo.notification.web.rest;

import com.dirac.demo.notification.service.NotificationKafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/notification-kafka")
public class NotificationKafkaResource {

    private final Logger log = LoggerFactory.getLogger(NotificationKafkaResource.class);

    private NotificationKafkaProducer kafkaProducer;

    public NotificationKafkaResource(NotificationKafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping(value = "/publish")
    public void sendMessageToKafkaTopic(@RequestParam("message") String message) {
        log.debug("REST request to send to Kafka topic the message : {}", message);
        this.kafkaProducer.sendMessage(message);
    }
}
