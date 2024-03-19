package com.example.blps2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.example.blps2.repo.AlbumRepository;
import com.example.blps2.repo.CredRepository;
import com.example.blps2.repo.ImageRepository;
import com.example.blps2.repo.RoleRepository;
import com.example.blps2.repo.UserRepository;
import com.example.blps2.repo.entity.Creds;
import com.example.blps2.repo.entity.UserDao;
import com.example.blps2.repo.request.UserBody;

import java.util.NoSuchElementException;

@Profile("sec")
@Configuration
@Service("user_service")
public class UserService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AlbumRepository albumRepository;

    private final TransactionTemplate transactionTemplate;

    @SuppressWarnings("null")
    @Autowired
    public UserService(PlatformTransactionManager transactionManager) {
        this.transactionTemplate = new TransactionTemplate(transactionManager);
        this.transactionTemplate.setTimeout(1);
        this.transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_SERIALIZABLE);
    }

    public void registerUser(UserBody user) throws Exception {
        if (user.getUsername().length() == 0 || user.getPasswdString().length() == 0) {
            throw new Exception();
        }

        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new Exception();

        }

        var hashedPasswd = encoder.encode(user.getPasswdString());
        var userDao = new UserDao();
        userDao.setUsername(user.getUsername());
        userDao.setRoles(roleRepository.findByName("ROLE_USER"));
        userRepository.save(userDao);

        var creads = new Creds();
        creads.setHashedPasswd(hashedPasswd);
        creads.setUsername(user.getUsername());
        CredRepository.add(creads);
    }

    public void banUser(String username) {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {

            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                try {
                    var user = userRepository.findByUsername(username).get();
                    albumRepository.deleteAllByUser(user);
                    user.setBlocked(true);
                    user.getRoles().clear();
                    userRepository.save(user);
                } catch (NoSuchElementException e) {
                    status.setRollbackOnly();
                }
            }

        });
    }
}
