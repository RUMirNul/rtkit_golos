package com.rtkit.golos.core.service.implementation;

import com.rtkit.golos.core.dto.InviteQueue;
import com.rtkit.golos.core.dto.InviteQueueDto;
import com.rtkit.golos.core.dto.StatDto;
import com.rtkit.golos.core.dto.UserDto;
import com.rtkit.golos.core.service.PublishService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Slf4j
@Service
public class PublishServiceImpl implements PublishService {
    private final RabbitTemplate rabbitTemplate;

    @Override
    public void publishRegisterMessage(UserDto userDto) {
        log.info("Добавление в очередь сообщения письма регистрации: {}", userDto);
        rabbitTemplate.convertAndSend("golos", "activation", userDto);
    }

    @Override
    public void publishInviteMessage(String name, int inviteId, InviteQueueDto inviteQueueDto) {
        for (String email : inviteQueueDto.emails())
        {
            log.info("Добавление в очередь сообщения о приглашения на почту: {}", email);
            rabbitTemplate.convertAndSend("golos", "invite", new InviteQueue(name, email, "localhost:8080/api/poll/" + inviteId));
        }
    }

    @Override
    public void publishStatMessage(StatDto statDto) {
        log.info("Добавление в очередь сообщения со статистикой: {}", statDto);
        rabbitTemplate.convertAndSend("golos", "stat", statDto);
    }
}
