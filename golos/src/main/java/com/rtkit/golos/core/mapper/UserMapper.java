package com.rtkit.golos.core.mapper;

import com.rtkit.golos.core.web.request.AddUserRequest;
import com.rtkit.golos.core.dto.UserDto;
import com.rtkit.golos.core.entity.GolosUser;
import com.rtkit.golos.core.entity.UserRole;
import org.mapstruct.*;

@Mapper(componentModel="spring", uses = {UserMapperUtil.class})
public interface UserMapper {
    UserDto toDto(GolosUser dto);

    @ValueMappings({
            @ValueMapping(source="ADMIN", target="ADMIN"),
            @ValueMapping(source="MODERATOR", target="MODERATOR"),
            @ValueMapping(source="USER", target="USER")
    })
    UserRole toUserRole(String roleType);

    @Mapping(target = "id", ignore = true)
    GolosUser toModel(AddUserRequest newUser);
}
