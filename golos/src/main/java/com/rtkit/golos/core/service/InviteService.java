package com.rtkit.golos.core.service;

import com.rtkit.golos.core.dto.InviteDto;
import com.rtkit.golos.core.web.request.AddInviteRequest;
import org.springframework.transaction.annotation.Transactional;

public interface InviteService {
    @Transactional
    InviteDto addInvite(Integer pollId, AddInviteRequest inviteDto);

    InviteDto getInvite(Integer inviteId);
}
