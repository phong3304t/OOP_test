package com.service;

import com.model.exceptions.UserNotFoundException;
import com.model.users.*;
import com.repository.UserRepository;

import java.sql.SQLException;

public class AuthService {
    private static final Admin admin = new Admin
            ("Admin", "admin@vnu.edu.vn", "123456789");

    public boolean loginAsUser(String email, String password)
            throws UserNotFoundException, SQLException {
        User user = UserRepository.findUserByEmail(email);
        return user.getPassword().equals(password);
    }

    public boolean loginAsAdmin(String email, String password) {
        return admin.getEmail().equals(email)
                & admin.getPassword().equals(password);
    }

    public void registerUser(String email, String password, String name)
            throws UserNotFoundException, SQLException {
        User user = new User (name, email, password, 0);
        User existingUser = UserRepository.findUserByEmail(user.getEmail());

        if (existingUser == null) {
            UserRepository.addUser(user);
            System.out.println("User registered successfully.");
        } else {
            System.out.println("Email already exists.");
        }
    }
}

