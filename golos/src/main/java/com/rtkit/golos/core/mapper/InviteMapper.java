package com.rtkit.golos.core.mapper;

import com.rtkit.golos.core.web.request.AddInviteRequest;
import com.rtkit.golos.core.dto.InviteDto;
import com.rtkit.golos.core.entity.Invite;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring", uses = {PollMapperUtil.class})
public interface InviteMapper {
    @Mapping(target = "uses", ignore = true)
    @Mapping(target = "expireDt", ignore = true)
    @Mapping(target = "createdDt", ignore = true)
    @Mapping(target = "id", ignore = true)
    Invite toEntity(AddInviteRequest inviteDto);

    InviteDto toDto(Invite invite);
}
