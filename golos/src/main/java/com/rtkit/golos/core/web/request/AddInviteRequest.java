package com.rtkit.golos.core.web.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddInviteRequest {
    private Integer pollId;
    private Integer maxUses;
}
