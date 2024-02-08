package com.rtkit.golos.core.web.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(description = "Запрос для создания записи о начале прохождения опроса.")
public class AddUserPollResultRequest {
    @NotNull(message = "Поле pollId не должно быть null.")
    @Schema(description = "Уникальный идентификатор опроса.")
    private Integer pollId;

    @NotNull(message = "Поле userId не должно быть null.")
    @Schema(description = "Уникальный идентификатор пользователя.")
    private Integer userId;
}
