package com.rtkit.golos.core.dto;

import com.rtkit.golos.core.entity.Poll;
import com.rtkit.golos.core.entity.PollStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PollDto {
    private Integer id;

    private Instant createdDt;

    private Integer authorId;

    private String name;

    private String description;

    private PollStatus status;

    public PollDto(Poll poll) {
        this.id = poll.getId();
        this.name = poll.getName();
        this.description = poll.getDescription();
        this.status = poll.getStatus();
        this.authorId = poll.getAuthorId().getId();
        this.createdDt = poll.getCreatedDt();
    }
}
