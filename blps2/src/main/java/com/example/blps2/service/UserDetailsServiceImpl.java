package com.example.blps2.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.blps2.repo.CredRepository;
import com.example.blps2.repo.RoleRepository;
import com.example.blps2.repo.UserRepository;
import com.example.blps2.repo.entity.Creds;
import com.example.blps2.repo.entity.UserDao;

@Profile("sec")
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Cannot find user with username '" + username + "'"));
        var creds = CredRepository.findUserByUsername(username);
        var roles = roleRepository.findByName("USER");
        var userDetails = new UserDetailsImpl();
        userDetails.setHashedPasswd(creds.getHashedPasswd());
        userDetails.setUsername(creds.getUsername());
        userDetails.setRoles(user.getRoles());
        return userDetails;

    }

}