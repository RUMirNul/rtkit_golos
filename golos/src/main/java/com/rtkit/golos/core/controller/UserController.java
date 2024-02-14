package com.rtkit.golos.core.controller;

import com.rtkit.golos.core.dto.PollDtoList;
import com.rtkit.golos.core.dto.UserPollResultDto;
import com.rtkit.golos.core.service.PollService;
import com.rtkit.golos.core.service.PublishService;
import com.rtkit.golos.core.service.UserPollResultService;
import com.rtkit.golos.core.service.UserService;
import com.rtkit.golos.core.web.request.AddUserRequest;
import com.rtkit.golos.core.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
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
@AllArgsConstructor
@Slf4j
@Tag(name = "Пользователи.", description = "Методы для работы с пользователями.")
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final PublishService publishService;
    private final PollService pollService;
    private final UserPollResultService userPollResultService;

    @GetMapping("/{userId}")
    @Operation(summary = "Получение пользователя.",
            description = "Возвращает пользователя с указанным id.",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Информация о пользователе.",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserDto.class)))
            })
    public ResponseEntity<?> getUserById(@PathVariable("userId") int userId){
        log.info("Запрос на получение опроса по id: {}", userId);

        UserDto userDto = userService.getUserById(userId);
        log.info("Полученный пользователь: {}", userDto);

        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/{userId}/polls")
    @Operation(summary = "Получение всех опросов пользователя.",
            description = "Возвращает список всех опросов пользователя.",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Информация об опросах пользователя.",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PollDtoList.class)))
            })
    public ResponseEntity<?> getPollsByUserId(@PathVariable("userId") int userId){
        log.info("Запрос на получение опроса по id: {}", userId);

        PollDtoList pollDtoList = new PollDtoList(pollService.getPollByUserId(userId));
        log.info("Полученный список опросов пользователя: {}", pollDtoList);

        return ResponseEntity.ok(pollDtoList);
    }

    @GetMapping("/{userId}/participation")
    @Operation(summary = "Получение всех пройденных опросов пользователя.",
            description = "Возвращает список всех пройденных опросов пользователя.",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Информация о пройденных опросах пользователя.",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = PollDtoList.class))))
            })
    public ResponseEntity<?> getParticipationByUserId(@PathVariable("userId") int userId){
        log.info("Запрос на получение пройденных опроса по id: {}", userId);

        List<UserPollResultDto> userPollResultDto = userPollResultService.getParticipationByUserId(userId);
        log.info("Полученный список опросов пользователя: {}", userPollResultDto);

        return ResponseEntity.ok(userPollResultDto);
    }

    @GetMapping("/")
    @Operation(summary = "Получение всех пользователь.",
            description = "Получение информации о всех пользователях.",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Список пользователь.",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = UserDto.class))))
            })
    public ResponseEntity<?> getAllUsers(){
        log.info("Запрос на получение пользователей");

        List<UserDto> userDtoList = userService.getAllUsers();
        log.info("Полученный список пользователей: {}", userDtoList);

        return ResponseEntity.ok(userDtoList);
    }

    @PostMapping
    @Operation(summary = "Создание пользователя.",
            description = "Добавление пользователя.",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Информация о созданном пользователе.",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserDto.class)))
            })
    public ResponseEntity<?> addUser(@RequestBody AddUserRequest request) {
        log.info("Запрос на добавление пользователя: {}", request);

        UserDto userDto = userService.addUser(request);
        log.info("Полученный пользователей: {}", userDto);

        if (userDto != null)
        {
            log.info("Отправка письма регистрации");
           // publishService.publishRegisterMessage(userDto);
        }

        return ResponseEntity.ok(userDto);
    }

    @PatchMapping("/{userId}/role/{userRole}")
    @Operation(summary = "Изменение роли пользователя.",
            description = "Обновление роли пользователя пользователя.",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Информация об обновленном пользователе.",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserDto.class)))
            })
    public ResponseEntity<?> updatePollStatus(@PathVariable("userId") int userId,
                                              @PathVariable("userRole") String userRole) {
        log.info("Запрос на изменение роли: {} пользователя по id: {}", userRole, userId);

        UserDto userDto = userService.updateUserRole(userId, userRole);
        log.info("Обновленный пользователей: {}", userDto);

        return ResponseEntity.ok(userDto);
    }
}
