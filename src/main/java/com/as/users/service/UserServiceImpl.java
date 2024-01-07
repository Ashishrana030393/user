package com.as.users.service;

import com.as.users.UserRepository;
import com.as.users.entity.User;
import com.as.users.exception.UserNotFound;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user){
        return this.userRepository.save(user);
    }

    @Override
    public Optional<User> findUserById(Long id){
        return this.userRepository.findById(id);
    }

    @Override
    public void deleteAllUser(){
        this.userRepository.deleteAll();
    }


    @Override
    public User updateUser(User userDto){
        Optional<User> byId = this.userRepository.findById(userDto.getId());
        return byId.map(u-> {
            u.setUsername(userDto.getUsername());
            u.setEmail(userDto.getEmail());
            return this.userRepository.save(u);
        }).orElseThrow(()->new UserNotFound("invalid user", "id is " + userDto.getId(),null));
    }
}
