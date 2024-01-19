package com.rtkit.golos.core.mapper;

import com.rtkit.golos.core.access.UserRepo;
import com.rtkit.golos.core.entity.GolosUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class UserMapper {
    private final UserRepo userRepo;

    public GolosUser fromId(Integer id) {
        return userRepo.getReferenceById(id);
    }

    public Integer fromId(GolosUser authorId) {
        return authorId.getId();
    }
}
