package com.rtkit.golos.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rtkit.golos.core.dto.InviteDto;
import com.rtkit.golos.core.dto.UserRegisterDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@AllArgsConstructor
@Slf4j
@Service
public class EmailConsumer {
    private JavaMailSender mailSender;
    private ObjectMapper mapper;

    @RabbitListener(queues = "invite")
    public void onInviteMessage(final String message) throws JsonProcessingException {
        log.info("Запрос приглашения: {} ", message);
        InviteDto inviteDto = mapper.readValue(message, InviteDto.class);
        final String subject = "Vote in poll: " + inviteDto.pollName();
        final String content = "Poll link:" + inviteDto.url();
        setEmail(createEmail(inviteDto.email(), subject, content));
    }

    @RabbitListener(queues = "activation")
    public void onActivationMessage(final UserRegisterDto userDto) throws JsonProcessingException {
        log.info("Запрос активации: {} ", userDto);
        String subject = "Here's your activation code:" + userDto.activationCode();
        String content = "Ignore";
        setEmail(createEmail(userDto.email(), subject, content));
    }

    public MimeMessagePreparator createEmail(String email, String subject, String content) {
        return mimeMessage -> {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            message.setTo(email);
            message.setFrom("${spring.mail.username}");
            message.setSubject(subject);
            message.setText(content);
        };
    }

    public void setEmail(MimeMessagePreparator mimeMessage) {
        try {
            mailSender.send(mimeMessage);
        } catch (HttpClientErrorException e) {
            log.error("Ошибка отправки письма");
            throw new AmqpRejectAndDontRequeueException(e);
        }
        log.info("Письмо отправлено");
    }
}
