package controller;

import dto.request.SignInDTO;
import dto.request.SignUpDTO;
import dto.response.ResponseMessenger;
import model.Role;
import model.RoleName;
import model.User;
import service.role.RoleServiceIMPL;
import service.user.IUserService;
import service.user.UserServiceIMPL;

import java.util.HashSet;
import java.util.Set;

public class UserController {

    IUserService userService = new UserServiceIMPL();

    public int getLastId() {
        return userService.getLastId();
    }

    public ResponseMessenger register(SignUpDTO signUpDTO) {
        if (userService.existsByUsername(signUpDTO.getUsername())) {
            return new ResponseMessenger("username_exists");
        }
        if (userService.existsByEmail(signUpDTO.getEmail())) {
            return new ResponseMessenger("email_exists");
        }
        Set<Role> roles = new HashSet<Role>();
        roles.add(new RoleServiceIMPL().findByRoleName(RoleName.USER));
        User user = new User(
                signUpDTO.getId(),
                signUpDTO.getName(),
                signUpDTO.getUsername(),
                signUpDTO.getPassword(),
                signUpDTO.getEmail(),
                roles
        );
        userService.save(user);
        return new ResponseMessenger("success");
    }

    public ResponseMessenger login(SignInDTO signInDTO) {
        User user = userService.findByUsername(signInDTO.getUsername());
        if (user == null) {
            return new ResponseMessenger("username_not_found");
        }
        if (!user.getPassword().equals(signInDTO.getPassword())) {
            return new ResponseMessenger("password_wrong");
        }

        userService.setCurrentUser(user);

        return new ResponseMessenger("success");
    }

    public User getCurrentUser() {
        return userService.getCurrentUser();
    }

    public void logout() {
        userService.setCurrentUser(null);
    }
}
