package com.rtkit.golos.core.controller;

import com.rtkit.golos.core.dto.*;
import com.rtkit.golos.core.service.InviteService;
import com.rtkit.golos.core.service.PollService;
import com.rtkit.golos.core.service.PublishService;
import com.rtkit.golos.core.web.request.AddInviteRequest;
import com.rtkit.golos.core.web.request.AddPollRequest;
import com.rtkit.golos.core.web.request.UpdatePollRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@Tag(name = "Опросы.", description = "Методы для работы с опросами.")
@RequestMapping("/poll")
public class PollController {
    private final PollService pollService;
    private final InviteService inviteService;
    private final PublishService publishService;

    @GetMapping("/status")
    @Operation(summary = "Получение списка статусов опросов.",
            description = "Возвращает массив статусов.",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Информация о статусах опросов.",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PollStatusDto.class)))
            })
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllStatues() {
        log.info("Запрос получения списка статусов опросов");

        PollStatusDto pollStatusDto = pollService.getAllStatuses();
        log.info("Полученный массив статусов: {}", pollStatusDto);

        return ResponseEntity.ok(pollStatusDto);
    }

    @GetMapping(path ="/{pollId}", produces=MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Получение опроса.",
            description = "Возвращает опрос с указанным id.",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Информация об опросе.",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PollDto.class)))
            })
    public ResponseEntity<?> getPollById(@PathVariable("pollId") int pollId) {
        log.info("Запрос на получение опроса по id: {}", pollId);

        PollDto pollDto = pollService.getPollById(pollId);
        log.info("Полученный опрос: {}", pollDto);

        return ResponseEntity.ok(pollDto);
    }

    @GetMapping
    @Operation(summary = "Получение всех опросов.",
            description = "Возвращает список всех опроса.",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Информация об опросах.",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PollDtoList.class)))
            })
    public ResponseEntity<?> getAllPolls() {
        log.info("Запрос на получение списка опросов");

        List<PollDto> foundPollDtoList = pollService.getAllPolls();
        PollDtoList pollDtoList = new PollDtoList(foundPollDtoList);
        log.info("Полученный список опросов: {}", pollDtoList);

        return ResponseEntity.ok(pollDtoList);
    }

    @PostMapping
    @Operation(summary = "Создание опроса.",
            description = "Добавление записи об опросе.",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Информация о созданном опросе.",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PollDto.class)))
            })
    public ResponseEntity<?> addPoll(@RequestBody AddPollRequest request) {
        log.info("Запрос на добавление опроса: {}", request);

        PollDto pollDto = pollService.addPoll(request);
        log.info("Созданный опрос: {}", pollDto);

        return ResponseEntity.ok(pollDto);
    }

    @PutMapping
    @Operation(summary = "Обновление опроса.",
            description = "Изменение информации об опросе.",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Информация об измененном опросе.",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PollDto.class)))
            })
    public ResponseEntity<?> updatePoll(@RequestBody UpdatePollRequest request) {
        log.info("Запрос на обновления опроса: {}", request);

        PollDto pollDto = pollService.updatePollDto(request);
        log.info("Измененный опрос: {}", pollDto);

        return ResponseEntity.ok(pollDto);
    }

    @PatchMapping("/{pollId}/status/{statusName}")
    @Operation(summary = "Обновление статуса опроса.",
            description = "Изменение статуса опроса.",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Информация об измененном опросе.",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PollDto.class)))
            })
    public ResponseEntity<?> updatePollStatus(@PathVariable("pollId") int pollId,
                                              @PathVariable("statusName") String statusName) {
        log.info("Запрос на изменение статуса: {} опроса по id: {}", statusName, pollId);

        PollDto pollDto = pollService.updatePollStatus(pollId, statusName);
        log.info("Полученный опрос: {}", pollId);

        return ResponseEntity.ok(pollDto);
    }

    @DeleteMapping("/{pollId}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Удаление опроса.",
            description = "Изменение статуса опроса.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Информация об измененном опросе.")
            })
    public ResponseEntity<?> deletePoll(@PathVariable("pollId") int pollId) {
        log.info("Запрос на удаление опроса по id: {}", pollId);

        boolean isDone = pollService.deletePoll(pollId);
        return ResponseEntity.ok(isDone);
    }

    @GetMapping("/{pollId}/invite/{inviteId}")
    @Operation(summary = "Получение списка приглашений.",
            description = "Вовзращает массив приглашений.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Информация о приглашениях.",
                        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = InviteDto.class)))
            })
    public ResponseEntity<?> getInvite(@PathVariable("inviteId") int inviteId,
                                       @PathVariable("pollId") int pollId) {
        log.info("Запрос получения приглашения по id: {}", inviteId);

        InviteDto inviteDto = inviteService.getInvite(inviteId);
        log.info("Полученное приглашения по id: {}", inviteId);

        return ResponseEntity.ok(inviteDto);
    }

    @PostMapping("/{pollId}/invite")
    @Operation(summary = "Создание приглашения.",
            description = "Добавление записи о приглашении.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Информация о приглашение.",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = InviteDto.class)))
            })
    public ResponseEntity<?> createInvite(@RequestBody AddInviteRequest request,
                                          @PathVariable("pollId") int pollId) {
        log.info("Запрос на создание приглашения: {}", request);

        InviteDto inviteDto = inviteService.addInvite(pollId, request);
        log.info("Созданное приглашение: {}", inviteDto);

        return ResponseEntity.ok(inviteDto);
    }

    @PostMapping("/{pollId}/invite/{inviteId}")
    @Operation(summary = "Создание рассылки приглашения.",
            description = "Выполнение почтовой рассылки приглашения.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Информация о рассылке.")
            })
    public void sendEmail(@PathVariable("pollId") int pollId,
                          @PathVariable("inviteId") int inviteId,
                          @RequestBody InviteQueueDto request) {
        log.info("Запрос на рассылку приглашений: {}", request);

        String pollName = pollService.getPollById(pollId).getName();
        log.info("Публикация приглашения в очередь");

        publishService.publishInviteMessage(pollName, inviteId, request);
    }
}