package com.rtkit.golos.core.service;

import com.rtkit.golos.core.dto.InviteQueueDto;
import com.rtkit.golos.core.dto.UserDto;
import org.example.StatResultPoll;

import java.util.List;

public interface PublishService {
    void publishRegisterMessage(UserDto userDto);

    void publishInviteMessage(String name, int inviteId, InviteQueueDto inviteQueueDto);

    void publishStatMessage(String email, List<StatResultPoll> statDto);
}
