package edu.alex.service;

import edu.alex.model.entity.User;

import java.util.List;


public interface UserService {
    User addUser(User user);

    List<User> getAllUsers();

    List<User> getUsersByFirstName(String firstName);
}
