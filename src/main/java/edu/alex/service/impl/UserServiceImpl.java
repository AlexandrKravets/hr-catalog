package edu.alex.service.impl;

import com.google.common.base.Preconditions;
import edu.alex.model.entity.User;
import edu.alex.model.repository.UserRepository;
import edu.alex.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public User addUser(User user) {
        Preconditions.checkNotNull(user, "User must not be null");

        return userRepository.save(user);
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public List<User> getUsersByFirstName(String firstName) {
        Preconditions.checkNotNull(firstName, "First name must not be null");

        return userRepository.findByFirstName(firstName);
    }
}
