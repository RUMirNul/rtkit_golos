package com.rtkit.golos.core.dto;

import com.rtkit.golos.core.entity.Poll;
import com.rtkit.golos.core.entity.PollStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.Instant;

@Data
@NoArgsConstructor
@Schema(description = "Опрос.")
public class PollDto {
    @Schema(description = "Уникальный идентификатор.")
    private Integer id;

    @Schema(description = "Дата создания.")
    private Instant createdDt;

    @NotNull(message = "Поле authorId не должно быть null.")
    @Schema(description = "Уникальный идентификатор автора опроса.")
    private Integer authorId;

    @NotNull(message = "Поле name не должно быть null.")
    @Length(max = 100, message = "Максимальная длина 100 символов.")
    @Schema(description = "Имя опроса.")
    private String name;

    @NotNull(message = "Поле description не должно быть null.")
    @Length(max = 300, message = "Максимальная длина 300 символов.")
    @Schema(description = "Описание опроса.")
    private String description;

    @NotNull(message = "Поле status не должно быть null.")
    @Length(max = 255, message = "Максимальная длина 255 символов.")
    @Schema(description = "Статус опроса.")
    private PollStatus status;

    public PollDto(Poll poll) {
        this.id = poll.getId();
        this.name = poll.getName();
        this.description = poll.getDescription();
        this.status = poll.getStatus();
        this.authorId = poll.getAuthorId().getId();
        this.createdDt = poll.getCreatedDt();
    }
}
