package com.ra.service;

import com.ra.model.dto.request.UserDTO;
import com.ra.model.dto.response.UserLoginResponse;
import com.ra.model.entity.User;

import java.util.List;

public interface UserService {
User getUserByUsername(String username);
User register(UserDTO userDTO);
UserLoginResponse login(UserDTO userDTO);

String updatePassword(User user, String oldPassword, String newPassword, String confirmPassword);
}
