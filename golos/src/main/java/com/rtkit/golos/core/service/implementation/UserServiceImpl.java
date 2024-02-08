package com.rtkit.golos.core.service.implementation;

import com.rtkit.golos.core.access.UserRepo;
import com.rtkit.golos.core.service.UserService;
import com.rtkit.golos.core.web.request.AddUserRequest;
import com.rtkit.golos.core.dto.UserDto;
import com.rtkit.golos.core.entity.GolosUser;
import com.rtkit.golos.core.entity.UserRole;
import com.rtkit.golos.core.exception.NotFoundException;
import com.rtkit.golos.core.mapper.UserMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final UserMapper userMapper;

    @Override
    public UserDto getUserById(Integer userId) {
        GolosUser foundUser = userRepo.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь с id:%d не найден.".formatted(userId)));
        log.info("Найденный результат: {}", foundUser);

        UserDto foundUserDto = userMapper.toDto(foundUser);
        log.info("Сопоставленный объект UserDto: {}", foundUserDto);

        return foundUserDto;
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<UserDto> userDtoList = userRepo.findAll().stream()
                .map(UserDto::new).toList();
        log.info("Найденный результат: {}", userDtoList);

        return userDtoList;
    }

    @Override
    @Transactional
    public UserDto addUser(AddUserRequest newUser) {
        GolosUser createdUser = userMapper.toEntity(newUser);
        GolosUser savedUser = userRepo.saveAndFlush(createdUser);
        log.info("Сохраненная сущность GolosUser: {}", savedUser);

        UserDto savedUserDto = userMapper.toDto(savedUser);
        log.info("Найденный результат: {}", savedUserDto);

        return savedUserDto;
    }

    @Override
    public UserDto updateUserRole(Integer userId, String role) {
        UserRole userRole = userMapper.toUserRole(role);
        GolosUser foundUser = userRepo.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь с id:%d не найден".formatted(userId)));
        log.info("Найденный результат: {}", foundUser);

        foundUser.setRole(userRole);
        GolosUser updatedUser = userRepo.save(foundUser);
        log.info("Сохраненная сущность GolosUser: {}", updatedUser);

        UserDto updatedUserDto = userMapper.toDto(updatedUser);
        log.info("Сопоставленный объект UserDto: {}", updatedUserDto);

        return updatedUserDto;
    }
}
