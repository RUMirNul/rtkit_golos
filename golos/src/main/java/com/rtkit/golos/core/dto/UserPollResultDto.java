package com.rtkit.golos.core.dto;

import com.rtkit.golos.core.entity.UserPollResult;
import com.rtkit.golos.core.entity.UserPollStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
public class UserPollResultDto {
    private Integer id;
    private Instant startDt;
    private UserPollStatus status;

    public UserPollResultDto(UserPollResult result) {
        this.id = result.getId();
        this.startDt = result.getStartedDt();
        this.status = result.getStatus();
    }
}
