package com.rtkit.golos.core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Массив статусов.")
public class PollStatusDto {
    @Schema(description = "Массив статусов.")
    private List<String> statuses;

    @Schema(description = "Размер массивов статусов.")
    private int size;
}
