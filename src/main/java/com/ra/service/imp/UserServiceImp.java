package com.ra.service.imp;

import com.ra.model.dto.request.UserDTO;
import com.ra.model.dto.response.UserLoginResponse;
import com.ra.model.entity.Role;
import com.ra.model.entity.User;
import com.ra.repository.UserRepository;
import com.ra.security.jwt.JWTProvider;
import com.ra.service.RoleService;
import com.ra.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Lazy
public class UserServiceImp  implements UserService{
    @Autowired
    private UserRepository userRepository;
@Autowired
private RoleService roleService;
@Autowired
private JWTProvider jwtProvider;

private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User register(UserDTO userDTO) {
        User userExists = userRepository.findByUsername(userDTO.getUsername());
        if (userExists != null) {
            return null ;
        }else {
            Role roleUser = roleService.findByRoleName("USER");
            User newUser = User
                    .builder()
                    .username(userDTO.getUsername())
                    .password(passwordEncoder.encode(userDTO.getPassword()))
                    .role(roleUser)
                    .build();
            try {
                return userRepository.save(newUser);
            }catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

    }

    @Override
    public UserLoginResponse login(UserDTO userDTO) {
        User userLogin = userRepository.findByUsername(userDTO.getUsername());
        if(userLogin!=null && passwordEncoder.matches(userDTO.getPassword(),userLogin.getPassword())){
            return UserLoginResponse
                    .builder()
                    .username(userDTO.getUsername())
                    .accessToken(jwtProvider.generateToken(userDTO.getUsername()))
                    .build();
        }else{
            return null;
        }
    }

    @Override
    public String updatePassword(User user, String oldPassword, String newPassword, String confirmPassword) {
        if(passwordEncoder.matches(oldPassword,user.getPassword())&&newPassword.equals(confirmPassword)){
            user.setPassword(newPassword);
            userRepository.save(user);
            return"changed password successfully!";
        }else{
            return "Passwords do not match!";
        }
    }
}
