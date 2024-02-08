package com.rtkit.golos.core.dto;

import com.rtkit.golos.core.entity.Invite;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.Instant;

@Data
@NoArgsConstructor
@Schema(description = "Приглашение.")
public class InviteDto {
    @Schema(description = "Уникальный идентификатор.")
    private Integer id;

    @Schema(description = "Дата создания.")
    private Instant createdDt;

    @Schema(description = "Дата прекращения действия.")
    private Instant expireDt;

    @Schema(description = "Количество использований.")
    private Integer uses;

    @Schema(description = "Максимальное количество использований.")
    private Integer maxUses;

    public InviteDto(Invite invite) {
        this.id = invite.getId();
        this.createdDt = invite.getCreatedDt();
        this.expireDt = invite.getExpireDt();
        this.uses = invite.getUses();
        this.maxUses = invite.getMaxUses();
    }
}
