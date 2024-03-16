package com.example.blps2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.blps2.repo.UserRepository;
import com.example.blps2.repo.entity.UserDao;
import com.example.blps2.repo.request.UserBody;

@Configuration
@Service("user_service")
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    public void registerUser(UserBody user) throws Exception {
        if (user.getUsername().length() == 0 || user.getPasswdString().length() == 0) {
            throw new Exception();
        }
        var hashedPasswd = encoder().encode(user.getPasswdString());
        var userDao = new UserDao();
        userDao.setHashedPasswd(hashedPasswd);
        userDao.setUsername(user.getUsername());
        userRepository.save(userDao);
    }

    public void login(UserBody user) {
        // Future security
    }
}
