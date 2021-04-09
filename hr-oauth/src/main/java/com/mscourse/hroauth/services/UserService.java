package com.mscourse.hroauth.services;

import com.mscourse.hroauth.entities.User;
import com.mscourse.hroauth.feingclients.UserFeingClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserFeingClient userFeingClient;

    public UserService(UserFeingClient userFeingClient) {
        this.userFeingClient = userFeingClient;
    }

    public User findByEmail(String email){
        User user = this.userFeingClient.findByEmail(email).getBody();

        if(user == null) {
            log.error("Email not found: {}", email);
            throw new IllegalArgumentException("Email not found: " + email);
        }

        log.info("Email found: {}", email);
        return user;
    }
}
