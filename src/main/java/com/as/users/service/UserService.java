package com.as.users.service;

import com.as.users.entity.User;

import java.util.Optional;

public interface UserService {
    User save(User user);

    Optional<User> findUserById(Long id);

    void deleteAllUser();
}
