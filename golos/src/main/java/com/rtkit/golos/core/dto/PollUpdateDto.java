package com.rtkit.golos.core.dto;

import com.rtkit.golos.core.entity.PollStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PollUpdateDto {
    private Integer id;
    private String name;
    private String description;
    private PollStatus status;
}
