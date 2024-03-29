package com.rtkit.golos.core.mapper;

import com.rtkit.golos.core.web.request.AddUserPollResultRequest;
import com.rtkit.golos.core.dto.UserPollResultDto;
import com.rtkit.golos.core.entity.UserPollResult;
import com.rtkit.golos.core.entity.UserPollStatus;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel="spring", uses = {UserMapperUtil.class, PollMapperUtil.class})
public interface UserPollResultMapper {
    @Mapping(target = "startDt", ignore = true)
    UserPollResultDto toDto(UserPollResult userPollResultEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "startedDt", ignore = true)
    @Mapping(target = "endedDt", ignore = true)
    @Mapping(target = "status", ignore = true)
    UserPollResult toEntity(AddUserPollResultRequest newResult);

    @ValueMappings({
            @ValueMapping(source="ONGOING", target="ONGOING"),
            @ValueMapping(source="DROPPED", target="DROPPED"),
            @ValueMapping(source="COMPLETED", target="COMPLETED"),
            @ValueMapping(source= MappingConstants.ANY_REMAINING, target="ONGOING")
    })
    UserPollStatus toUserPollResult(String statusName);

    List<UserPollResultDto> toDto(List<UserPollResult> byUserId);
}
