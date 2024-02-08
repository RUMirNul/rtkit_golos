package com.rtkit.golos.core.service;

import com.rtkit.golos.core.dto.UserDto;
import com.rtkit.golos.core.web.request.AddUserRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {
    UserDto getUserById(Integer userId);

    List<UserDto> getAllUsers();

    @Transactional
    UserDto addUser(AddUserRequest newUser);

    UserDto updateUserRole(Integer userId, String role);
}
