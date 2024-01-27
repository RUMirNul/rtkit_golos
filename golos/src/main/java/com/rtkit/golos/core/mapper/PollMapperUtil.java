package com.rtkit.golos.core.mapper;

import com.rtkit.golos.core.access.PollRepo;
import com.rtkit.golos.core.entity.Poll;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class PollMapperUtil {
    private final PollRepo pollRepo;

    public Poll fromId(Integer id) {
        return pollRepo.getReferenceById(id);
    }
}
