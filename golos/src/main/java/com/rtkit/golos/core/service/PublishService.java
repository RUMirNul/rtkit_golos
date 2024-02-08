package com.rtkit.golos.core.service;

import com.rtkit.golos.core.dto.InviteQueueDto;
import com.rtkit.golos.core.dto.StatDto;
import com.rtkit.golos.core.dto.UserDto;

public interface PublishService {
    void publishRegisterMessage(UserDto userDto);

    void publishInviteMessage(String name, int inviteId, InviteQueueDto inviteQueueDto);

    void publishStatMessage(StatDto statDto);
}
