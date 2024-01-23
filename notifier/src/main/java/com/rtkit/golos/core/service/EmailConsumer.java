package com.rtkit.golos.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {
    Logger log = LoggerFactory.getLogger(EmailConsumer.class.getName());

    @RabbitListener(queues = "promotion")
    public void onPushMessage(final String dto) {
        log.info(dto);
    }
}
