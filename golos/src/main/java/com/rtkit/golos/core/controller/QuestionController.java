package com.rtkit.golos.core.controller;


import com.rtkit.golos.core.dto.PollQuestionDto;
import com.rtkit.golos.core.dto.QuestionAnswerDto;
import com.rtkit.golos.core.mapper.QuestionMapper;
import com.rtkit.golos.core.service.QuestionService;
import com.rtkit.golos.core.web.request.AddPollQuestionRequest;
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
@Tag(name = "Вопросы.", description = "Методы для работы с вопросами.")
@Slf4j
@AllArgsConstructor
public class QuestionController {
    private final QuestionService questionService;
    private final QuestionMapper questionMapper;

    @PostMapping
    @Operation(summary = "Создание вопроса.",
            description = "Добавление вопроса в опрос с указанным id.",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Сохраненный вопрос.",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = PollQuestionDto.class)))
            })
    public PollQuestionDto createQuestion(@RequestBody AddPollQuestionRequest request) {
        log.info("Запрос сохранения нового вопроса: {}", request);

        PollQuestionDto pqd = questionMapper.toPollQuestionDto(request);
        return questionService.addQuestion(pqd);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение вопроса.",
            description = "Возвращает вопрос с указанным id.",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Вопрос.",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = PollQuestionDto.class)))
            })
    public PollQuestionDto getQuestion(@PathVariable("id") int id) {
        log.info("Запрос получения вопроса по id: {}", id);

        return questionService.getPollQuestionById(id);
    }
}