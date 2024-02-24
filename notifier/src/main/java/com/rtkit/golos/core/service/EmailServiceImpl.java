package com.rtkit.golos.core.service;

import jakarta.activation.DataSource;
import jakarta.mail.util.ByteArrayDataSource;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.FileDto;
import org.example.PollInviteDto;
import org.example.UserRegisterDto;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Slf4j
@Service
public class EmailServiceImpl implements EmailService {
    private JavaMailSender mailSender;

    @Override
    @RabbitListener(queues = "invite")
    public void onInviteMessage(final PollInviteDto inviteDto) {
        log.info("Запрос приглашения: {} ", inviteDto);
        final String subject = "Vote in poll: " + inviteDto.getPollName();
        final String content = "Poll link:" + inviteDto.getUrl();
        setEmail(createEmail(inviteDto.getEmail(), subject, content, null));
    }

    @Override
    @RabbitListener(queues = "activation")
    public void onActivationMessage(final UserRegisterDto userDto) {
        log.info("Запрос активации: {} ", userDto);
        String subject = "Here's your activation code:" + userDto.getActivationCode();
        String content = "Ignore";
        setEmail(createEmail(userDto.getEmail(), subject, content, null));
    }

    @RabbitListener(queues = "statMail")
    public void onStatMessage(final FileDto fileDto) {
        log.info("Запрос отправки статистики на электронную почту: {} ", fileDto.getEmail());
        final String subject = "Статистика по опросу: " + 1;
        final String content = "Poll link:";
        DataSource fds = new ByteArrayDataSource(fileDto.getExcelFile(), "application/vnd.ms-excel");
        setEmail(createEmail(fileDto.getEmail(), subject, content, fds));
    }

    @Override
    public MimeMessagePreparator createEmail(String email, String subject, String content, DataSource src) {
        return mimeMessage -> {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            message.setTo(email);
            message.setFrom("${spring.mail.username}");
            message.setSubject(subject);
            message.setText(content);
            if (src != null)
                message.addAttachment("stat.xlsx", src);
        };
    }

    @Override
    public void setEmail(MimeMessagePreparator mimeMessage) {
        log.info("Подготовка к отправке письма.");
        try {
            mailSender.send(mimeMessage);
        } catch (MailException e) {
            log.error("Ошибка отправки письма.");
            throw new AmqpRejectAndDontRequeueException(e);
        }
        log.info("Письмо отправлено.");
    }
}
