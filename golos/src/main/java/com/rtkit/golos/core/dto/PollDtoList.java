package com.rtkit.golos.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Массив опросов.")
public class PollDtoList {
    @Schema(description = "Список опросов.")
    List<PollDto> items;
}
