package com.rtkit.golos.core.dto;

import com.rtkit.golos.core.entity.PollStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PollCreateDto {
    private Integer authorId;
    private String name;
    private String description;
    private PollStatus status;
}
