package com.rtkit.golos.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InviteDto {
    private Integer id;
    private Instant createdDt;
    private Instant expireDt;
    private Integer uses;
    private Integer maxUses;
}
