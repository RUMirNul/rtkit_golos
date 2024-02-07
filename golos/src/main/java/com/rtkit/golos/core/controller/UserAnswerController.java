package com.rtkit.golos.core.controller;


import com.rtkit.golos.core.dto.UserAnswerDto;
import com.rtkit.golos.core.service.UserAnswerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("api")
public class UserAnswerController {
    private final UserAnswerService userAnswerService;

    @PostMapping("/question/submit")
    public UserAnswerDto saveUserAnswer(@RequestBody UserAnswerDto request) {
        log.info("Запрос на сохранение ответа пользователя: {}", request);

        return userAnswerService.saveUserAnswer(request);
    }

    @GetMapping("/question/{pqid}/submit/{rid}")
    public UserAnswerDto getUserAnswer(@PathVariable("pqid") Integer pollQuestionId, @PathVariable("rid") Integer resultId) {
        log.info("Запрос на получение ответа пользователя: pollQuestionId = {}, resultId = {}",pollQuestionId, resultId);

        return userAnswerService.getUserAnswer(resultId, pollQuestionId);
    }

    @PutMapping("/question/submit")
    public UserAnswerDto updateUserAnswer(@RequestBody UserAnswerDto userAnswer) {
        log.info("Запрос обновления ответа пользователя: {}", userAnswer);

        return userAnswerService.updateUserAnswer(userAnswer);
    }

    @DeleteMapping("/question/{pqid}/submit/{rid}")
    public void deleteUserAnswer(@PathVariable("pqid") Integer pollQuestionId, @PathVariable("rid") Integer resultId) {
        log.info("Запрос удаления ответа пользователя: pollQuestionId = {}, resultId = {}",pollQuestionId, resultId);

        userAnswerService.deleteUserAnswer(resultId, pollQuestionId);
    }
}
