package com.codedecode.userinfo.service;

import com.codedecode.userinfo.dto.UserDTO;
import com.codedecode.userinfo.entity.User;
import com.codedecode.userinfo.mapper.UserMapper;
import com.codedecode.userinfo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    public UserDTO save(UserDTO userDTO) {
        User savedUser = userRepo.save(UserMapper.INSTANCE.mapUserDTOToUser(userDTO));

        return  UserMapper.INSTANCE.mapUserToUserDto(savedUser);

    }

    public ResponseEntity<UserDTO> getUserById(Integer userId) {
        Optional<User> user = userRepo.findById(userId);
        if (user.isPresent())
            return new ResponseEntity<>(UserMapper.INSTANCE.mapUserToUserDto(user.get()), HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
