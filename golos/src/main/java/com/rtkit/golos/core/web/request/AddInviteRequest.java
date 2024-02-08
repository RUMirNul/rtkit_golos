package com.rtkit.golos.core.web.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@Schema(description = "Запрос для создания приглашения.")
public class AddInviteRequest {
    @Schema(description = "Максимальное количество использований.")
    private Integer maxUses;
}
