package com.as.users.controller;

import com.as.users.entity.User;
import com.as.users.mapper.UserMapper;
import com.as.users.request.UserRm;
import com.as.users.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    User save(@RequestBody @Valid UserRm request){
        User user = UserMapper.INSTANCE.toUser(request);
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

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    User updateUser(@RequestBody User user){
        return this.userService.updateUser(user);
    }
}
