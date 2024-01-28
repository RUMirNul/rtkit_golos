package com.rtkit.golos.core.web.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddUserPollResultRequest {
    private Integer pollId;
    private Integer userId;
}
