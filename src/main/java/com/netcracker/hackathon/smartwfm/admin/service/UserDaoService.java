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

    public User getAuthenticatedUser(String emailId, String password) {
        User user = userRepository.findUserByEmailId(emailId);
        if (user.getPassword().equals(password)) {
            return user;
        }
        throw new UserNotFoundException("User's credentials are invalid");
    }

    public User getUserByEmailId(String email) {
        return userRepository.findUserByEmailId(email);
    }

    public void save(User user) {
        //String password = randomPasswordGenerator.generateRandomPassword(6);
        String password = "netcracker";
        user.setPassword(password);
        userRepository.save(user);
        /*try {
            String body = "Hello " + user.getUserName() + ", Your SMARTWFM account has been created. please login using username: " +
                    user.getEmailId() + " and password: " + password;
            mailCalendarService.sendMailNotification(SMARTWFM_ACCOUNT_CREATED, body, fromEmail, "manvendrav@gmail.com");
        } catch (Exception exception) {
           // throw exception;
        }*/
    }
}
