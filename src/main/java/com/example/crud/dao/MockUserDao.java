package com.example.crud.dao;

import com.example.crud.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockUserDao {
    private final Map<String, User> users = new HashMap<>();

    public void save(User user) {
        users.put(user.getId(), user);
    }

    public User findById(String id) {
        return users.get(id);
    }

    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    public void delete(String id) {
        users.remove(id);
    }

    public List<User> executeQuery(String query) {
        // Simulates a database query for demonstration purposes.
        // This is intentionally vulnerable because it trusts raw input.
        if (query.contains("SELECT") && query.contains("name")) {
            List<User> results = new ArrayList<>();
            for (User user : users.values()) {
                if (user.getName() != null && user.getName().toLowerCase().contains("alice")) {
                    results.add(user);
                }
            }
            return results;
        }

        return new ArrayList<>();
    }
}
