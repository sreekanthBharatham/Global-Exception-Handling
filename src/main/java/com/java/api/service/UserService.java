package com.java.api.service;

import com.java.api.common.UserStatus;
import com.java.api.dto.User;
import com.java.api.entity.UserEntity;
import com.java.api.exception.EntityNotFoundException;
import com.java.api.exception.UserAlreadyRegisteredException;
import com.java.api.exception.config.GlobalErrorCode;
import com.java.api.repository.UserRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import lombok.RequiredArgsConstructor;

import com.javatodev.api.client.InstantWebToolsApiClient;


/*
 * @Service
 * 
 * @RequiredArgsConstructor public class UserService { private final
 * UserRepository userRepository;
 * 
 * public void createUser(User user){ UserEntity userEntity = new UserEntity();
 * userEntity.setUsername(user.getUsername());
 * userEntity.setPassword(UUID.randomUUID().toString());
 * userEntity.setUserStatus(UserStatus.PENDING);
 * userRepository.save(userEntity); }
 * 
 * public User readUserByUsername(String username) { Optional<UserEntity>
 * userEntitiesByUsername = userRepository.findUserEntitiesByUsername(username);
 * User user = new User();
 * BeanUtils.copyProperties(userEntitiesByUsername.get(), user); return user; }
 * 
 * }
 */



@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final InstantWebToolsApiClient instantWebToolsApiClient;
    
    
    public void createUser(User user){
        Optional<UserEntity> userEntitiesByUsername = userRepository.findUserEntitiesByUsername(user.getUsername());
        if (userEntitiesByUsername.isPresent()) {
            throw new UserAlreadyRegisteredException("User already registered under given username", GlobalErrorCode.ERROR_USER_ALREADY_REGISTERED);
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(UUID.randomUUID().toString());
        userEntity.setUserStatus(UserStatus.PENDING);
        userEntity.setAirline(user.getAirline());
        userRepository.save(userEntity);
    }

//    public void createUser(User user){
//        Optional<UserEntity> userEntitiesByUsername = userRepository.findUserEntitiesByUsername(user.getUsername());
//        if (userEntitiesByUsername.isPresent()) {
//            throw new UserAlreadyRegisteredException("exception.user.already.registered", GlobalErrorCode.ERROR_USER_ALREADY_REGISTERED);
//        }
//        UserEntity userEntity = new UserEntity();
//        userEntity.setUsername(user.getUsername());
//        userEntity.setPassword(UUID.randomUUID().toString());
//        userEntity.setUserStatus(UserStatus.PENDING);
//        userEntity.setAirlineId(user.getAirlineId());
//        userRepository.save(userEntity);
//    }

//    public User readUserByUsername(String username) {
//        UserEntity userEntity = userRepository.findUserEntitiesByUsername(username).orElseThrow(EntityNotFoundException::new);
//        User user = new User();
//        BeanUtils.copyProperties(userEntity, user);
//        user.setAirlineId(readAirline(user.getAirlineId()));
//        return user;
//    }
    
    
    public User readUserByUsername(String username) {
        UserEntity userEntity = userRepository.findUserEntitiesByUsername(username).orElseThrow(EntityNotFoundException::new);
        User user = new User();
        BeanUtils.copyProperties(userEntity, user);
        user.setAirline(readAirline(user.getAirline()));
        return user;
    }

    public String readAirline (String id){
        return instantWebToolsApiClient.readAirLineById(id);
    }
}
