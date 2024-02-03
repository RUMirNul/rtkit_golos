package com.rtkit.golos.core.service;

import com.rtkit.golos.core.access.InviteRepo;
import com.rtkit.golos.core.mapper.PollMapperUtil;
import com.rtkit.golos.core.web.request.AddInviteRequest;
import com.rtkit.golos.core.dto.InviteDto;
import com.rtkit.golos.core.entity.Invite;
import com.rtkit.golos.core.mapper.InviteMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class InviteService {
    private InviteRepo inviteRepo;
    private InviteMapper inviteMapper;
    private PollMapperUtil pollMapperUtil;

    @Transactional
    public Invite addInvite(Integer pollId, AddInviteRequest inviteDto) {
        Invite newInvite = inviteMapper.toEntity(inviteDto);
        newInvite.setPollId(pollMapperUtil.fromId(pollId));
        newInvite.setUses(0);
        return inviteRepo.save(newInvite);
    }

    public InviteDto getInvite(Integer inviteId) {
        return inviteMapper.toDto(inviteRepo.getReferenceById(inviteId));
    }
}
