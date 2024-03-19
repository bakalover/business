package com.example.blps2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.blps2.repo.CredRepository;
import com.example.blps2.repo.UserRepository;

@Profile("sec")
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Cannot find user with username '" + username + "'"));
        var creds = CredRepository.findUserByUsername(username);
        var userDetails = new UserDetailsImpl();
        userDetails.setHashedPasswd(creds.getHashedPasswd());
        userDetails.setUsername(creds.getUsername());
        userDetails.setRoles(user.getRoles());
        return userDetails;

    }

}