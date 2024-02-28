package com.rtkit.golos.core.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.FileDto;
import org.example.StatResultPoll;
import org.example.StatResultPollList;
import org.jxls.common.JxlsException;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jxls.transform.poi.JxlsPoiTemplateFillerBuilder;

@AllArgsConstructor
@Slf4j
@Service
public class StatServiceImpl implements StatService {
    public static final String STAT_TEMPLATE_XLSX = "/templates/template.xlsx";

    @Override
    @RabbitListener(queues = "stat")
    public FileDto onStatMessage(final StatResultPollList resultList) {
        log.info("Запрос статистики: {} ", resultList);
        try {
            return new FileDto(resultList.getEmail(), exportPollStat(resultList.getPollAnswerDtoList()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public byte[] exportPollStat(List<StatResultPoll> resultDtoList) throws IOException {
        Map<String, Object> data = new HashMap<>();
        data.put("result", resultDtoList);
        log.info("Формирование электронного отчета статистики.");
        try {
            byte[] resultByteArray = JxlsPoiTemplateFillerBuilder.newInstance()
                    .withTemplate(getClass().getResourceAsStream(STAT_TEMPLATE_XLSX))
                    .buildAndFill(data);
            log.info("Отчет создан.");
            return resultByteArray;
        }
        catch (NullPointerException | JxlsException e) {
            log.info("Не найден шаблон.");
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }
}
