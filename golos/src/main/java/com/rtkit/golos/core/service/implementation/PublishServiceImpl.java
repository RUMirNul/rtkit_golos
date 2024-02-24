package com.rtkit.golos.core.service.implementation;

import com.rtkit.golos.core.dto.*;
import com.rtkit.golos.core.service.PublishService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.FileDto;
import org.example.PollInviteDto;
import org.example.StatResultPoll;
import org.example.StatResultPollList;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

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
            rabbitTemplate.convertAndSend("golos", "invite", new PollInviteDto(name, email, "localhost:8080/api/poll/" + inviteId));
        }
    }

    @Override
    public void publishStatMessage(String email, List<StatResultPoll> statDto) {
        log.info("Добавление в очередь сообщения со статистикой: {}", statDto);
        byte[] resultFileByteArray = (byte[]) rabbitTemplate.convertSendAndReceive("golos", "stat", new StatResultPollList(statDto));
        if (resultFileByteArray != null) {
            log.info("Добавление в очередь сообщения об отправке статистики на почту: {}", email);
            rabbitTemplate.convertAndSend("golos", "statMail", new FileDto(email, resultFileByteArray));
        } else
            log.info("Произошла ошибка при формировании статистики.");

    }
}
