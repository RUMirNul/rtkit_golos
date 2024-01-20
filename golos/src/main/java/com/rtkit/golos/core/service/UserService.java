package com.rtkit.golos.core.service;

import com.rtkit.golos.core.access.UserRepo;
import com.rtkit.golos.core.dto.UserCreateDto;
import com.rtkit.golos.core.dto.UserDto;
import com.rtkit.golos.core.entity.GolosUser;
import com.rtkit.golos.core.entity.UserRole;
import com.rtkit.golos.core.exception.NotFoundException;
import com.rtkit.golos.core.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class UserService {
    private final UserRepo userRepo;
    private final UserMapper userMapper;

    public UserDto getUserById(Integer userId) {
        return userMapper.toDto(userRepo.getReferenceById(userId));
    }

    public List<UserDto> getAllUsers() {
        List<UserDto> userList = new ArrayList<>();
        for (GolosUser user : userRepo.findAll())
            userList.add(new UserDto(user));
        return userList;
    }

    public UserDto addUser(UserCreateDto newUser) {
        GolosUser createdUser = userMapper.toModel(newUser);
        GolosUser createdPoll = userRepo.saveAndFlush(createdUser);
        return userMapper.toDto(createdPoll);
    }

    public UserDto updateUserRole(Integer userId, String role) {
        UserRole userRole = userMapper.toUserRole(role);
        GolosUser foundUser = userRepo.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь с id:%d не найден".formatted(userId)));
        foundUser.setRole(userRole);
        GolosUser updatedUser = userRepo.save(foundUser);
        return userMapper.toDto(updatedUser);
    }
}
