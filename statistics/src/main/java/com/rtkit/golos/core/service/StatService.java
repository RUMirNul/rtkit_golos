package com.rtkit.golos.core.service;

import org.example.FileDto;
import org.example.StatResultPoll;
import org.example.StatResultPollList;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import java.io.IOException;
import java.util.List;

public interface StatService {
    @RabbitListener(queues = "stat")
    FileDto onStatMessage(StatResultPollList resultDto);

    byte[] exportPollStat(List<StatResultPoll> resultDtoList) throws IOException;
}
