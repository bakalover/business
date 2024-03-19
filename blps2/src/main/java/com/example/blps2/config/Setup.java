package com.example.blps2.config;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.ApplicationArguments;
// import org.springframework.boot.ApplicationRunner;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.boot.context.event.ApplicationReadyEvent;
// import org.springframework.context.ApplicationListener;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Component;

// import com.example.blps2.repo.RoleRepository;
// import com.example.blps2.repo.entity.Role;

// @Component
// public class Setup implements ApplicationListener<ApplicationReadyEvent> {

//     private static Logger logger = LoggerFactory.getLogger(Setup.class);

//     @Autowired
//     private RoleRepository roleRepository;

//     @Autowired
//     private PasswordEncoder encoder;

//     @Override
//     public void onApplicationEvent(ApplicationReadyEvent event) {
//         logger.info(encoder.encode("moderator"));

//         logger.info(encoder.encode("admin"));
//         // var userRole = new Role();
//         // var moderatorRole = new Role();
//         // var adminRole = new Role();

//         // userRole.setName("ROLE_USER");
//         // moderatorRole.setName("ROLE_MODERATOR");
//         // adminRole.setName("ROLE_ADMIN");

//         // roleRepository.save(userRole);
//         // roleRepository.save(moderatorRole);
//         // roleRepository.save(adminRole);
//     }

// }