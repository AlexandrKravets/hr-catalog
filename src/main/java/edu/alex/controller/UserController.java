package edu.alex.controller;

import edu.alex.model.entity.User;
import edu.alex.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(method = RequestMethod.GET,
            params = "firstName",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getUsersByFirstName(@RequestParam String firstName) {
        return userService.getUsersByFirstName(firstName);
    }
}
