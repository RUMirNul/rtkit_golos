package com.rtkit.golos.core.controller;

import com.rtkit.golos.core.dto.AddQuestionAnswerDto;
import com.rtkit.golos.core.dto.QuestionAnswerDto;
import com.rtkit.golos.core.mapper.QuestionAnswerMapper;
import com.rtkit.golos.core.service.QuestionAnswerService;
import com.rtkit.golos.core.web.request.AddQuestionAnswerRequest;
import com.rtkit.golos.core.web.request.UpdateQuestionAnswerRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/answer")
@Tag(name = "Ответы на вопросы.", description = "Методы для работы с результатами опроса пользователя.")
@Slf4j
@AllArgsConstructor
public class QuestionAnswerController {
    private QuestionAnswerService questionAnswerService;
    private QuestionAnswerMapper questionAnswerMapper;

    @PostMapping
    @Operation(summary = "Создание ответов к вопросу.",
            description = "Добавление ответа к вопросу с указанным id.",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Сохраненные ответы.",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = QuestionAnswerDto.class)))
            })
    public ResponseEntity<?> addQuestionAnswer(@RequestBody AddQuestionAnswerRequest request) {
        log.info("Запрос на добавление ответов к вопросу: {}", request);

        AddQuestionAnswerDto addQuestionAnswerDto = questionAnswerMapper.addQuestionAnswerRequestToAddQuestionAnswerDto(request);
        List<QuestionAnswerDto> result = questionAnswerService.addNewQuestionAnswer(addQuestionAnswerDto);
        log.info("Созданные ответы на вопрос: {}", result);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{pollQuestionId}")
    @Operation(summary = "Получение ответов на вопрос.",
            description = "Возвращает ответы на вопрос с указанным id.",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Ответы на вопрос.",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = QuestionAnswerDto.class)))
            })
    public ResponseEntity<?> getQuestionAnswersByPollQuestionId(@PathVariable("pollQuestionId") int pollQuestionId) {
        log.info("Запрос получения ответов для вопроса с id = {}", pollQuestionId);

        List<QuestionAnswerDto> result = questionAnswerService.getQuestionAnswersByPollQuestionId(pollQuestionId);
        log.info("Полученные ответы на вопрос: {}", result);

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{answerId}")
    @Operation(summary = "Удаление ответа на вопрос.",
            description = "Удаляет ответ на вопрос с указанным id вопроса.",
            responses = {
                    @ApiResponse(responseCode = "200")
            })
    public void deleteQuestionAnswerByAnswerId(@PathVariable("answerId") int answerId) {
        log.info("Запрос удаления ответа с id = {}", answerId);

        questionAnswerService.deleteQuestionAnswer(answerId);
    }

    @PutMapping
    @Operation(summary = "Обновление ответа на вопрос.",
            description = "Обновляет ответ на вопрос с указанным id и возвращает обновленный ответ.",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Обновленный ответ.",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = QuestionAnswerDto.class)))
            })
    public ResponseEntity<?> updateQuestionAnswer(@RequestBody UpdateQuestionAnswerRequest request) {
        log.info("Запрос на обновление ответа: {}", request);

        QuestionAnswerDto questionAnswerDto = questionAnswerMapper.toDto(request);
        log.info("Сопоставленная сущность: {}", questionAnswerDto);

        QuestionAnswerDto updatedQuestionAnswer = questionAnswerService.updateQuestionAnswer(questionAnswerDto);
        log.info("Обновленный ответ на вопрос: {}", updatedQuestionAnswer);

        return ResponseEntity.ok(updatedQuestionAnswer);
    }
}
