package com.service;

import com.model.exceptions.UserNotFoundException;
import com.model.users.*;
import com.repository.UserRepository;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    public void addUser(User user) {
        UserRepository.addUser(user);
        System.out.println("User added successfully.");
    }

    public User findUserByEmail(String email)
            throws UserNotFoundException, SQLException {
        User user = UserRepository.findUserByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("User with email " + email + " not found.");
        }
        return user;
    }

    public List<User> getUsers() throws SQLException {
        return UserRepository.getUser();
    }
}

