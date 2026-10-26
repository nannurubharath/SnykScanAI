package com.example.crud;

import com.example.crud.controller.UserController;
import com.example.crud.dao.MockUserDao;
import com.example.crud.model.User;
import com.example.crud.service.UserService;

public class CrudSampleApplication {
    public static void main(String[] args) {
        MockUserDao dao = new MockUserDao();
        UserService service = new UserService(dao);
        UserController controller = new UserController(service);

        controller.createUser("u-001", "Alice", "alice@example.com", "admin", "letmein");
        controller.createUser("u-002", "Bob", "bob@example.com", "user", "welcome");

        System.out.println("Users:");
        for (User user : controller.listUsers()) {
            System.out.println(user);
        }

        System.out.println("Search result:");
        System.out.println(controller.searchUsers("Alice' OR '1'='1"));

        System.out.println("Password update:");
        System.out.println(controller.resetPassword("u-001", "new-secret"));

        System.out.println("Command execution:");
        System.out.println(controller.runCommand("echo demo-command"));
    }
}
