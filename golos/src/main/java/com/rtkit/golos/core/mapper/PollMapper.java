package com.rtkit.golos.core.mapper;

import com.rtkit.golos.core.dto.PollCreateDto;
import com.rtkit.golos.core.dto.PollDto;
import com.rtkit.golos.core.dto.PollUpdateDto;
import com.rtkit.golos.core.entity.Poll;
import com.rtkit.golos.core.entity.PollStatus;
import org.mapstruct.*;

@Mapper(componentModel="spring", uses = {UserMapperUtil.class})
public interface PollMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "authorId", source = "authorId")
    Poll toModel(PollDto dto);

    @Mapping(target = "authorId", source = "authorId")
    PollDto toDto(Poll createdPoll);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDt", ignore = true)
    PollDto toDto(PollCreateDto dto);

    @Mapping(target = "authorId", ignore = true)
    @Mapping(target = "createdDt", ignore = true)
    PollDto toDto(PollUpdateDto dto);

    @ValueMappings({
            @ValueMapping(source="HIDDEN", target="HIDDEN"),
            @ValueMapping(source="PUBLIC", target="PUBLIC"),
            @ValueMapping(source="PRIVATE", target="PRIVATE"),
            @ValueMapping(source="CLOSED", target="CLOSED"),
            @ValueMapping(source="DRAFT", target="DRAFT"),
            @ValueMapping(source=MappingConstants.NULL, target="DRAFT"),
    })
    PollStatus toPollStatus(String dateType);
}
