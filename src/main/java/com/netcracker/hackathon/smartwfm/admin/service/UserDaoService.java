package com.netcracker.hackathon.smartwfm.admin.service;

import com.netcracker.hackathon.smartwfm.admin.model.User;
import com.netcracker.hackathon.smartwfm.linemanager.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDaoService {
    @Autowired
    private UserRepository userRepository;

    // TODO go for password Encryption from the UI and set the encrypted password
    // TODO in the table instead of plain string
    public User getAuthenticatedUser(String userName, String password) {
        User user = userRepository.findUserByUserName(userName);
        if(user.getPassword().equals(password)) {
            return user;
        }
        throw new UserNotFoundException("User's credentials are invalid");
    }

    public void save(User user) {
        userRepository.save(user);
    }
}
