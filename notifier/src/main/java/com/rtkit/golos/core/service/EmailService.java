package com.rtkit.golos.core.service;

import jakarta.activation.DataSource;
import org.example.PollInviteDto;
import org.example.UserRegisterDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.javamail.MimeMessagePreparator;

public interface EmailService {
    @RabbitListener(queues = "invite")
    void onInviteMessage(PollInviteDto inviteDto);

    @RabbitListener(queues = "activation")
    void onActivationMessage(UserRegisterDto userDto);

    MimeMessagePreparator createEmail(String email, String subject, String content, DataSource src);

    void setEmail(MimeMessagePreparator mimeMessage);
}
