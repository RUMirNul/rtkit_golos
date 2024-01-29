package com.rtkit.golos.core.service;

import com.rtkit.golos.core.dto.InviteQueue;
import com.rtkit.golos.core.dto.InviteQueueDto;
import com.rtkit.golos.core.dto.StatDto;
import com.rtkit.golos.core.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Slf4j
@Service
public class PublishService {
    private final RabbitTemplate rabbitTemplate;

    public void publishRegisterMessage(UserDto userDto) {
        log.info("Sending registration message: " + userDto);
        rabbitTemplate.convertAndSend("golos", "activation", userDto);
    }

    public void publishInviteMessage(String name, int inviteId, InviteQueueDto inviteQueueDto) {
        for (String email : inviteQueueDto.emails())
        {
            log.info("Sending invite to " + email);
            rabbitTemplate.convertAndSend("golos", "invite", new InviteQueue(name, email, "localhost:8080/api/poll/" + inviteId));
        }
    }

    public void publishStatMessage(StatDto statDto) {
        log.info("Sending stat message" + statDto);
        rabbitTemplate.convertAndSend("golos", "stat", statDto);
    }
}
