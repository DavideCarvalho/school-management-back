package com.growth.schoolmanagement.controller;

import com.growth.schoolmanagement.dto.UserDTO;
import com.growth.schoolmanagement.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(UserController.USER_ENDPOINT)
@AllArgsConstructor(onConstructor = @__({@Lazy}))
public class UserController {
    public static final String USER_ENDPOINT = "/api/v1/user";

    private final UserService service;

    @GetMapping
    public List<UserDTO> findAll() {
        return this.service.findAll();
    }
}
