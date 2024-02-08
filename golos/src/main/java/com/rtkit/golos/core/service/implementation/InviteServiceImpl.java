package com.rtkit.golos.core.service.implementation;

import com.rtkit.golos.core.access.InviteRepo;
import com.rtkit.golos.core.exception.NotFoundException;
import com.rtkit.golos.core.mapper.PollMapperUtil;
import com.rtkit.golos.core.service.InviteService;
import com.rtkit.golos.core.web.request.AddInviteRequest;
import com.rtkit.golos.core.dto.InviteDto;
import com.rtkit.golos.core.entity.Invite;
import com.rtkit.golos.core.mapper.InviteMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@AllArgsConstructor
public class InviteServiceImpl implements InviteService {
    private InviteRepo inviteRepo;
    private InviteMapper inviteMapper;
    private PollMapperUtil pollMapperUtil;

    @Override
    @Transactional
    public InviteDto addInvite(Integer pollId, AddInviteRequest inviteDto) {
        Invite newInvite = inviteMapper.toEntity(inviteDto);
        newInvite.setPollId(pollMapperUtil.fromId(pollId));
        newInvite.setUses(0);
        Invite savedInvite = inviteRepo.save(newInvite);
        InviteDto createdInviteDto = new InviteDto(savedInvite);
        log.info("Сохраненное приглашение: {}", createdInviteDto);
        return createdInviteDto;
    }

    @Override
    public InviteDto getInvite(Integer inviteId) {
        Invite foundInvite = inviteRepo.findById(inviteId)
                .orElseThrow(() -> new NotFoundException("Пришлашение с id = %d не найден.".formatted(inviteId)));
        log.info("Найденное приглашение: {}", foundInvite);

        InviteDto inviteDto = inviteMapper.toDto(inviteRepo.getReferenceById(inviteId));
        log.info("Сопоставленный объект InviteDto: {}", inviteDto);

        return inviteDto;
    }
}
