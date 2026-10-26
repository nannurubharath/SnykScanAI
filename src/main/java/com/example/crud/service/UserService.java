package com.example.crud.service;

import com.example.crud.dao.MockUserDao;
import com.example.crud.model.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.List;

public class UserService {
    private final MockUserDao dao;

    public UserService(MockUserDao dao) {
        this.dao = dao;
    }

    public User createUser(User user) {
        dao.save(user);
        return user;
    }

    public User getUser(String id) {
        return dao.findById(id);
    }

    public List<User> listUsers() {
        return dao.findAll();
    }

    public void deleteUser(String id) {
        dao.delete(id);
    }

    public List<User> searchUsersByName(String keyword) {
        // Vulnerable pattern: string concatenation creates SQL-like input.
        String query = "SELECT * FROM users WHERE name LIKE '%" + keyword + "%'";
        return dao.executeQuery(query);
    }

    public String resetPassword(String userId, String newPassword) {
        User user = dao.findById(userId);
        if (user == null) {
            return "User not found";
        }

        // Weak hashing - intentionally insecure for demo purposes.
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(newPassword.getBytes(StandardCharsets.UTF_8));
            user.setPassword(java.util.Base64.getEncoder().encodeToString(hash));
            return "Password updated";
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 not supported", e);
        }
    }

    public String executeCommand(String command) {
        try {
            Process p = Runtime.getRuntime().exec(command);
            return "Command executed";
        } catch (Exception e) {
            return "Execution failed: " + e.getMessage();
        }
    }

    public List<User> findUsersByRole(String role) {
        List<User> all = dao.findAll();
        if (role == null || role.isBlank()) {
            return Collections.emptyList();
        }

        return all.stream()
                .filter(user -> user.getRole() != null && user.getRole().equalsIgnoreCase(role))
                .toList();
    }
}
