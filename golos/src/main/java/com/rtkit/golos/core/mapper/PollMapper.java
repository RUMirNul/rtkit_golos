package com.rtkit.golos.core.mapper;

import com.rtkit.golos.core.web.request.AddPollRequest;
import com.rtkit.golos.core.dto.PollDto;
import com.rtkit.golos.core.web.request.UpdatePollRequest;
import com.rtkit.golos.core.entity.Poll;
import com.rtkit.golos.core.entity.PollStatus;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel="spring", uses = {UserMapperUtil.class})
public interface PollMapper {
    @Mapping(target = "authorId", source = "authorId")
    Poll toEntity(PollDto dto);

    @Mapping(target = "authorId", source = "authorId")
    PollDto toDto(Poll createdPoll);

    @Mapping(target = "status", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDt", ignore = true)
    PollDto toDto(AddPollRequest dto);

    @Mapping(target = "authorId", ignore = true)
    @Mapping(target = "createdDt", ignore = true)
    PollDto toDto(UpdatePollRequest dto);

    @ValueMappings({
            @ValueMapping(source="HIDDEN", target="HIDDEN"),
            @ValueMapping(source="PUBLIC", target="PUBLIC"),
            @ValueMapping(source="PRIVATE", target="PRIVATE"),
            @ValueMapping(source="CLOSED", target="CLOSED"),
            @ValueMapping(source="DRAFT", target="DRAFT"),
            @ValueMapping(source=MappingConstants.NULL, target="DRAFT"),
            @ValueMapping(source=MappingConstants.ANY_REMAINING, target="DRAFT"),
    })
    PollStatus toPollStatus(String dateType);

    List<PollDto> toDto(List<Poll> byUserId);
}
