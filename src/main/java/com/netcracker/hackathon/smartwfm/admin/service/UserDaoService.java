package com.netcracker.hackathon.smartwfm.admin.service;

import com.netcracker.hackathon.smartwfm.admin.model.User;
import com.netcracker.hackathon.smartwfm.linemanager.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserDaoService {

    private static final String SMARTWFM_ACCOUNT_CREATED = "Your SMARTWFM Account has been created";
    @Value("${mail.from}")
    private String fromEmail;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RandomPasswordGenerator randomPasswordGenerator;

    @Autowired
    private MailCalendarService mailCalendarService;

    // TODO go for password Encryption from the UI and set the encrypted password
    // TODO in the table instead of plain string
    public User getAuthenticatedUser(String emailId, String password) {
        User user = userRepository.findUserByEmailId(emailId);
        if (user.getPassword().equals(password)) {
            return user;
        }
        throw new UserNotFoundException("User's credentials are invalid");
    }

    public void save(User user) {
        String password = randomPasswordGenerator.generateRandomPassword(6);
        user.setPassword(password);
        userRepository.save(user);
        try {
            String body = "Hello " + user.getUserName() + ", Your SMARTWFM account has been created. please login using username: " +
                    user.getEmailId() + " and password: " + password;
            mailCalendarService.sendMailNotification(SMARTWFM_ACCOUNT_CREATED, body, fromEmail, "sandip.magar@netcracker.com");
        } catch (Exception exception) {
            throw exception;
        }
    }
}
