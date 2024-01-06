package com.as.users.controller;

import com.as.users.entity.User;
import com.as.users.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    User save(@RequestBody User user){
        return this.userService.save(user);
    }

    @GetMapping
    Optional<User> findUserById(@RequestParam("id") Long id){
        return this.userService.findUserById(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    void deleteAll(){
        this.userService.deleteAllUser();
    }
}
