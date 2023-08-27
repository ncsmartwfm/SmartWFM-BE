package com.netcracker.hackathon.smartwfm.admin.service;

import com.netcracker.hackathon.smartwfm.admin.model.User;
import com.netcracker.hackathon.smartwfm.linemanager.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserDaoService {

    @Value("${mail.from}")
    private String fromEmail;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailCalendarService mailCalendarService;

    // TODO go for password Encryption from the UI and set the encrypted password
    // TODO in the table instead of plain string
    public User getAuthenticatedUser(String emailId, String password) {
        User user = userRepository.findUserByEmailId(emailId);
        if(user.getPassword().equals(password)) {
            return user;
        }
        throw new UserNotFoundException("User's credentials are invalid");
    }

    public void save(User user) {
        // TODO Logic to generate random password
        userRepository.save(user);
        try {
            mailCalendarService.sendSimpleEmail(
                    new CalendarRequest.Builder()
                            .withSubject("Your SMARTWFM Account has been created")
                            .withBody("Hello " + user.getUserName() + ", Your SMARTWFM account has been created. please login using username: " +
                                    user.getEmailId() + " and password: " + user.getPassword())
                            .withFromEmail(fromEmail)
                            .withToEmail("sandip.magar@netcracker.com")
                            .build()
            );
        }catch (Exception exception){
            exception.printStackTrace();
        }

    }
}
