package com.netcracker.hackathon.smartwfm.admin.controller;

import com.netcracker.hackathon.smartwfm.admin.model.User;
import com.netcracker.hackathon.smartwfm.admin.service.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {
    @Autowired
    private UserDaoService userDaoService;

    @PostMapping("/users")
    public ResponseEntity<User> save(@Validated @RequestBody User user) {
        try {
            userDaoService.save(user);
        } catch (Exception ex) {
            return (ResponseEntity<User>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    // TODO get an encrypted password from UI
    @GetMapping("/users")
    public List<String> authenticateUserCredentials(@RequestParam String emailId, @RequestParam String password) {
        User user = userDaoService.getAuthenticatedUser(emailId, password);
        return user.getRole();
    }

    @GetMapping("/users/{emailId}")
    public ResponseEntity<User> getUserByEmailId(@PathVariable String email) {
        User user = userDaoService.getUserByEmailId(email);
        if(null != user) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
