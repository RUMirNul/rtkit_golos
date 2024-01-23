package com.rtkit.golos.core.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserPollResultCreateDto {
    private Integer pollId;
    private Integer userId;
}
