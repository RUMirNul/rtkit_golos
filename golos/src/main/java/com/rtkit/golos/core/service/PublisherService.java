package com.rtkit.golos.core.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class PublisherService {
    Logger logger = LoggerFactory.getLogger(PublisherService.class.getName());

    private final RabbitTemplate rabbitTemplate;

    public void publishMessage() {
        String message = "m";

        logger.info("Sending message: " + message);
        rabbitTemplate.convertAndSend("golos", "promotion", message);
    }
}
