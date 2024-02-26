package com.rtkit.golos.core.controller;


import com.rtkit.golos.core.dto.UserAnswerDto;
import com.rtkit.golos.core.service.UserAnswerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/question")
@Tag(name = "Ответы пользователей.", description = "Методы для работы с ответами пользователей на вопросы.")
@AllArgsConstructor
@Slf4j
public class UserAnswerController {
    private final UserAnswerService userAnswerService;

    @PostMapping("/submit")
    @Operation(summary = "Сохранение ответа пользователя на вопрос.",
            description = "Добавление ответа пользователя на вопрос.",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Сохраненный ответ пользователя.",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserAnswerDto.class)))
            })
    public UserAnswerDto saveUserAnswer(@RequestBody UserAnswerDto request) {
        log.info("Запрос на сохранение ответа пользователя: {}", request);

        return userAnswerService.saveUserAnswer(request);
    }

    @GetMapping("/{pqid}/submit/{rid}")
    @Operation(summary = "Получение ответа пользователя на вопрос.",
            description = "Получение ответа пользователя на вопрос.",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Ответ пользователя.",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserAnswerDto.class)))
            })
    public UserAnswerDto getUserAnswer(@PathVariable("pqid") Integer pollQuestionId, @PathVariable("rid") Integer resultId) {
        log.info("Запрос на получение ответа пользователя: pollQuestionId = {}, resultId = {}", pollQuestionId, resultId);

        return userAnswerService.getUserAnswer(resultId, pollQuestionId);
    }

    @PutMapping("/submit")
    @Operation(summary = "Изменение ответа пользователя на вопрос.",
            description = "Изменение ответа пользователя на вопрос.",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Обновленный ответ пользователя.",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserAnswerDto.class)))
            })
    public UserAnswerDto updateUserAnswer(@RequestBody UserAnswerDto userAnswer) {
        log.info("Запрос обновления ответа пользователя: {}", userAnswer);

        return userAnswerService.updateUserAnswer(userAnswer);
    }

    @DeleteMapping("/{pqid}/submit/{rid}")
    @Operation(summary = "Удаление ответа пользователя на вопрос.",
            description = "Удаление ответа пользователя на вопрос по id вопроса.",
            responses = {
                    @ApiResponse(responseCode = "200")
            })
    public void deleteUserAnswer(@PathVariable("pqid") Integer pollQuestionId, @PathVariable("rid") Integer resultId) {
        log.info("Запрос удаления ответа пользователя: pollQuestionId = {}, resultId = {}", pollQuestionId, resultId);

        userAnswerService.deleteUserAnswer(resultId, pollQuestionId);
    }
}
