package com.netcracker.hackathon.smartwfm.admin.controller;

import com.netcracker.hackathon.smartwfm.admin.model.User;
import com.netcracker.hackathon.smartwfm.admin.service.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {
    @Autowired
    private UserDaoService userDaoService;

    @PostMapping("/users")
    public ResponseEntity<Object> save(@Validated @RequestBody User user) {
        userDaoService.save(user);
        return ResponseEntity.created(null).build();
    }

    // TODO get an encrypted password from UI
    @GetMapping("/users")
    public List<String> authenticateUserCredentials(@RequestParam String emailId, @RequestParam String password) {
        User user = userDaoService.getAuthenticatedUser(emailId, password);
        return user.getRole();
    }
}
