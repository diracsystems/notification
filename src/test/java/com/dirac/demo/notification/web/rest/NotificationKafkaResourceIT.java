package com.dirac.demo.notification.web.rest;

import com.dirac.demo.notification.NotificationApp;
import com.dirac.demo.notification.config.TestSecurityConfiguration;
import com.dirac.demo.notification.service.NotificationKafkaProducer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EmbeddedKafka
@SpringBootTest(classes = {NotificationApp.class, TestSecurityConfiguration.class})
public class NotificationKafkaResourceIT {

    @Autowired
    private NotificationKafkaProducer kafkaProducer;

    private MockMvc restMockMvc;

    @BeforeEach
    public void setup() {
        NotificationKafkaResource kafkaResource = new NotificationKafkaResource(kafkaProducer);

        this.restMockMvc = MockMvcBuilders.standaloneSetup(kafkaResource)
            .build();
    }

    @Test
    public void sendMessageToKafkaTopic() throws Exception {
        restMockMvc.perform(post("/api/notification-kafka/publish?message=yolo"))
            .andExpect(status().isOk());
    }
}
