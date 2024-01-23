package com.rtkit.golos.core.controller;

import com.rtkit.golos.core.dto.AddQuestionAnswerDto;
import com.rtkit.golos.core.dto.QuestionAnswerDto;
import com.rtkit.golos.core.mapper.QuestionAnswerMapper;
import com.rtkit.golos.core.service.QuestionAnswerService;
import com.rtkit.golos.core.web.request.AddQuestionAnswerRequest;
import com.rtkit.golos.core.web.request.UpdateQuestionAnswerRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/answer")
@Slf4j
@AllArgsConstructor
public class QuestionAnswerController {
    private QuestionAnswerService questionAnswerService;
    private QuestionAnswerMapper questionAnswerMapper;

    @PostMapping
    public ResponseEntity<?> addQuestionAnswer(@RequestBody AddQuestionAnswerRequest request) {
        log.info("Запрос на добавление ответов к вопросу: {}", request);

        AddQuestionAnswerDto addQuestionAnswerDto = questionAnswerMapper.addQuestionAnswerRequestToAddQuestionAnswerDto(request);
        List<QuestionAnswerDto> result = questionAnswerService.addNewQuestionAnswer(addQuestionAnswerDto);
        log.info("Созданные ответы на вопрос: {}", result);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{pollQuestionId}")
    public ResponseEntity<?> getQuestionAnswersByPollQuestionId(@PathVariable("pollQuestionId") int pollQuestionId) {
        log.info("Запрос получения ответов для вопроса с id = {}", pollQuestionId);

        List<QuestionAnswerDto> result = questionAnswerService.getQuestionAnswersByPollQuestionId(pollQuestionId);
        log.info("Полученные ответы на вопрос: {}", result);

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{answerId}")
    public void deleteQuestionAnswerByAnswerId(@PathVariable("answerId") int answerId) {
        log.info("Запрос удаления ответа с id = {}", answerId);

        questionAnswerService.deleteQuestionAnswer(answerId);
    }

    @PutMapping
    public ResponseEntity<?> updateQuestionAnswer(@RequestBody UpdateQuestionAnswerRequest request) {
        log.info("Запрос на обновление ответа: {}", request);

        QuestionAnswerDto questionAnswerDto = questionAnswerMapper.toDto(request);
        log.info("Сопоставленная сущность: {}", questionAnswerDto);

        QuestionAnswerDto updatedQuestionAnswer = questionAnswerService.updateQuestionAnswer(questionAnswerDto);
        log.info("Обновленный ответ на вопрос: {}", updatedQuestionAnswer);

        return ResponseEntity.ok(updatedQuestionAnswer);
    }
}
