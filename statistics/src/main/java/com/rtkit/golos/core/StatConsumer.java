package com.rtkit.golos.core;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jxls.transform.poi.JxlsPoiTemplateFillerBuilder;

@AllArgsConstructor
@Slf4j
@Service
public class StatConsumer {
    @RabbitListener(queues = "stat")
    public void onStatMessage(final ResultDto resultDto) {
        log.info("Запрос статистики: {} ", resultDto);
        try {
            List<ResultDto> resultDtoList = new ArrayList<>();
            resultDtoList.add(resultDto);
            exportPollStat(resultDtoList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void exportPollStat(List<ResultDto> resultDtoList) throws IOException {
        try {
            Map<String, Object> data = new HashMap<>();
            data.put("results", resultDtoList);
            log.info("Формирование файла статистики");
            JxlsPoiTemplateFillerBuilder.newInstance()
                    .withTemplate("template.xlsx")
                    .buildAndFill(data, new File("report.xlsx"));
        }
        catch (FileNotFoundException e) {
            log.info("Не найден шаблон");
            throw new AmqpRejectAndDontRequeueException(e);
        }
        log.info("Файл статистики создан");
    }
}
