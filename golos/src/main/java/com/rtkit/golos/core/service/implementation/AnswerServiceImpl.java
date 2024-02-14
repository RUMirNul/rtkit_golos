package com.rtkit.golos.core.service.implementation;

import com.rtkit.golos.core.access.AnswerRepository;
import com.rtkit.golos.core.dto.AnswerDto;
import com.rtkit.golos.core.entity.Answer;
import com.rtkit.golos.core.exception.NotFoundException;
import com.rtkit.golos.core.mapper.AnswerMapper;
import com.rtkit.golos.core.service.AnswerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
public class AnswerServiceImpl implements AnswerService {
    private AnswerRepository answerRepository;
    private AnswerMapper answerMapper;

    @Transactional
    @Override
    public Answer saveAnswer(AnswerDto answerDto) {
        log.info("Запрос сохранения нового ответа на вопрос: {}", answerDto);

        Answer answerEntity = answerMapper.toEntity(answerDto);
        log.info("Сопоставленный объект Answer: {}", answerEntity);

        Answer savedAnswer = answerRepository.save(answerEntity);
        log.info("Сохраненный ответ в БД: {}", savedAnswer);

        return savedAnswer;
    }

    @Override
    public AnswerDto getAnswer(int id) {
        log.info("Запрос получения ответа по id = {}", id);

        Answer foundAnswer = answerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Ответ с id = %d не найден.".formatted(id)));
        log.info("Найденный ответ: {}", foundAnswer);

        AnswerDto foundAnswerDto = answerMapper.toDto(foundAnswer);
        log.info("Сопоставленный объект AnswerDto: {}", foundAnswerDto);

        return foundAnswerDto;
    }

    @Override
    public void deleteAnswer(int id) {
        log.info("Запрос удаления вопроса с id = {}", id);

        answerRepository.deleteById(id);
    }
}
