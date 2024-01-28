package com.rtkit.golos.core.web.request;

import com.rtkit.golos.core.entity.PollStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddPollRequest {
    private Integer authorId;
    private String name;
    private String description;
    private PollStatus status;
}
