package com.rtkit.golos.core.service;

import com.rtkit.golos.core.access.UserAnswerRepository;
import com.rtkit.golos.core.dto.UserAnswerDto;
import com.rtkit.golos.core.entity.UserAnswer;
import com.rtkit.golos.core.mapper.UserAnswerMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class UserAnswerService {
    private final UserAnswerRepository userAnswerRepository;
    private final UserAnswerMapper userAnswerMapper;

    public UserAnswerDto saveUserAnswer(UserAnswerDto newUserAnswer) {
        log.info("Добавление нового ответа пользователя: {}", newUserAnswer);

        UserAnswer userAnswer = userAnswerMapper.toEntity(newUserAnswer);
        log.info("Сопоставленная сущность: {}", userAnswer);

        UserAnswer savedAnswer = userAnswerRepository.save(userAnswer);
        log.info("Сохраненный ответ пользователя: {}", savedAnswer);

        UserAnswerDto savedUserAnswerDto = userAnswerMapper.toDto(savedAnswer);
        log.info("Сопоставленная сущность: {}", savedUserAnswerDto);

        return savedUserAnswerDto;
    }

    public UserAnswerDto getUserAnswer(int resultId, int pollQuestionId) {
        log.info("Получение ответа пользователя по resultId = {} и pollQuestionId = {}", resultId, pollQuestionId);

        UserAnswer findedUserAnswer = userAnswerRepository.findByIdResultIdAndIdPollQuestionId(resultId, pollQuestionId);
        log.info("Найденная сущность: {}", findedUserAnswer);

        UserAnswerDto findedUserAnswerDto = userAnswerMapper.toDto(findedUserAnswer);
        log.info("Сопоставленная сущность: {}", findedUserAnswerDto);

        return findedUserAnswerDto;
    }


    public UserAnswerDto updateUserAnswer(UserAnswerDto newUserAnswer) {
        log.info("Обновление ответа пользователя на {}", newUserAnswer);
        deleteUserAnswer(newUserAnswer.getResultId(), newUserAnswer.getPollQuestionId());

        UserAnswerDto updatedAnswer = saveUserAnswer(newUserAnswer);
        log.info("Обновленный ответ пользователя: {}", updatedAnswer);

        return updatedAnswer;
    }

    public void deleteUserAnswer(int resultId, int pollQuestionId) {
        log.info("Удаление ответа пользователя по resultId = {} и pollQuestionId = {}", resultId, pollQuestionId);

        UserAnswer findedUserAnswer = userAnswerRepository.findByIdResultIdAndIdPollQuestionId(resultId, pollQuestionId);
        log.info("Найденная сущность: {}", findedUserAnswer);

        userAnswerRepository.delete(findedUserAnswer);
    }
}
