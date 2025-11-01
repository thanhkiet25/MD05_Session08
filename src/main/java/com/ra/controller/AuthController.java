package com.ra.controller;

import com.ra.model.dto.request.UserDTO;
import com.ra.model.dto.response.UserLoginResponse;
import com.ra.model.entity.User;
import com.ra.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO) {
        User user = userService.register(userDTO);
        if (user == null) {
            return new ResponseEntity<>("Username existed , register failed !", HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {
        UserLoginResponse userLoginResponse = userService.login(userDTO);
        if (userLoginResponse == null) {
            return new ResponseEntity<>("Username or password does not exist !", HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(userLoginResponse, HttpStatus.OK);
        }
    }
}
