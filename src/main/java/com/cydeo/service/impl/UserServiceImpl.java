package com.cydeo.service.impl;

import com.cydeo.dto.UserDTO;
import com.cydeo.entity.User;
import com.cydeo.mapper.UserMapper;
import com.cydeo.repository.UserRepository;
import com.cydeo.service.UserService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMap;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMap) {
        this.userRepository = userRepository;
        this.userMap = userMap;
    }

    @Override
    public List<UserDTO> listAllUsers() {

        List<User> userList = userRepository.findAll(Sort.by("firstName"));

        return userList.stream().map(userMap::convertToDto).collect(Collectors.toList());
    }

    @Override
    public UserDTO findByUserName(String username) {
        User user= userRepository.findByUserName(username);
        return userMap.convertToDto(user);
    }

    @Override
    public void save(UserDTO user) {
        userRepository.save(userMap.convertToEntity(user));
    }

    @Override
    public void deleteByUserName(String username) {

    }
}
