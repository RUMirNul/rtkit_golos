package com.rtkit.golos.core.controller;

import com.rtkit.golos.core.dto.UserPollResultDto;
import com.rtkit.golos.core.service.PollService;
import com.rtkit.golos.core.service.PublishService;
import com.rtkit.golos.core.service.UserPollResultService;
import com.rtkit.golos.core.service.UserService;
import com.rtkit.golos.core.service.implementation.StatisticsService;
import com.rtkit.golos.core.web.request.AddUserPollResultRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.StatResultPoll;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/poll/participation")
@Tag(name = "Результаты опросы пользователя.", description = "Методы для работы с результами опроса пользователя.")
public class UserPollResultController {
    private final UserPollResultService userPollResultService;
    private final PublishService publishService;
    private final StatisticsService statService;

    @GetMapping("/{resultId}")
    @Operation(summary = "Получение результата опроса пользователя.",
            description = "Возвращает результат опроса пользователя с указанным id.",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Информация о пользователе.",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserPollResultDto.class)))
            })
    public ResponseEntity<?> getResultById(@PathVariable("resultId") int resultId){
        log.info("Запрос на получение результата опроса по id: {}", resultId);

        UserPollResultDto userPollResultDto = userPollResultService.getResultById(resultId);
        log.info("Полученный результат опроса: {}", userPollResultDto);

        return ResponseEntity.ok(userPollResultDto);
    }

    @PostMapping
    @Operation(summary = "Создание результата опроса пользователя.",
            description = "Добавление результата опроса пользователя с указанным id.",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Информация о результатах опроса пользователя.",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserPollResultDto.class)))
            })
    public ResponseEntity<?> addPoll(@RequestBody AddUserPollResultRequest request) {
        log.info("Запрос на добавление результата опроса: {}", request);

        UserPollResultDto userPollResultDto = userPollResultService.addResult(request);
        log.info("Созданный результат опроса: {}", userPollResultDto);

        return ResponseEntity.ok(userPollResultDto);
    }

    @PatchMapping("/{resultId}/status/{statusName}")
    @Operation(summary = "Обновление статуса результата опроса пользователя.",
            description = "Изменение статуса результата опроса пользователя с указанным id.",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Информация о результатах опроса пользователя.",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserPollResultDto.class)))
            })
    public ResponseEntity<?> updatePollStatus(@PathVariable("resultId") int resultId,
                                              @PathVariable("statusName") String statusName) {
        log.info("Запрос на изменение статуса: statusName {}, resultId: {}", statusName, resultId);

        UserPollResultDto userPollResultDto = userPollResultService.updateResultPollStatus(resultId, statusName);
        log.info("Измененный результат опроса: {}", userPollResultDto);

        return ResponseEntity.ok(userPollResultDto);
    }

    @GetMapping("/{pollId}/statistics")
    @Operation(summary = "Отправка статистики по результата опроса.",
            description = "Отправка статистики по результата опроса с указанным id.",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Статистика о результатах опроса пользователя.",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserPollResultDto.class)))
            })
    public void getStatistics(@PathVariable("pollId") int pollId) {
        log.info("Запрос на получение статистики по результатам опроса с id: {}", pollId);
        List<StatResultPoll> resultPollDto = statService.formStatistics(pollId);
        log.info("Сформированная статистика: {}", resultPollDto);
        publishService.publishStatMessage(SecurityContextHolder.getContext().getAuthentication().getName(), resultPollDto);
    }
}
