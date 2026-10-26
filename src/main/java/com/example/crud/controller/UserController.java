package com.example.crud.controller;

import com.example.crud.model.User;
import com.example.crud.service.UserService;

import java.util.List;

public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public User createUser(String id, String name, String email, String role, String password) {
        User user = new User(id, name, email, role, password);
        return userService.createUser(user);
    }

    public User getUser(String id) {
        return userService.getUser(id);
    }

    public List<User> listUsers() {
        return userService.listUsers();
    }

    public void deleteUser(String id) {
        userService.deleteUser(id);
    }

    public List<User> searchUsers(String keyword) {
        return userService.searchUsersByName(keyword);
    }

    public String resetPassword(String userId, String newPassword) {
        return userService.resetPassword(userId, newPassword);
    }

    public String runCommand(String command) {
        return userService.executeCommand(command);
    }
}
