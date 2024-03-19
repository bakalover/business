// package com.example.blps2.config;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.ApplicationListener;
// import org.springframework.context.event.ContextRefreshedEvent;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Component;

// import com.example.blps2.repo.PrivilegeRepository;
// import com.example.blps2.repo.RoleRepository;
// import com.example.blps2.repo.UserRepository;
// import com.example.blps2.repo.entity.Privilege;
// import com.example.blps2.repo.entity.Role;
// import com.example.blps2.repo.entity.UserDao;

// import jakarta.transaction.Transactional;

// import java.util.Arrays;
// import java.util.List;
// import java.util.Collection;

// @Component
// public class RoleSetup implements
//         ApplicationListener<ContextRefreshedEvent> {


//     @Autowired
//     private RoleRepository roleRepository;

//     @Autowired
//     private PrivilegeRepository privilegeRepository;

//     @Autowired
//     private UserRepository userRepository;

//     @Autowired
//     private PasswordEncoder passwordEncoder;

//     @Override
//     public void onApplicationEvent(@SuppressWarnings("null") ContextRefreshedEvent event) {
//         Privilege readPrivilege = createPrivilege("READ_PRIVILEGE");
//         Privilege writePrivilege = createPrivilege("WRITE_PRIVILEGE");

//         List<Privilege> adminPrivileges = Arrays.asList(
//                 readPrivilege, writePrivilege);

//         createRole("ROLE_ADMIN", adminPrivileges);
//         createRole("ROLE_USER", Arrays.asList(readPrivilege));

//         List<Role> adminRole = roleRepository.findByName("ROLE_ADMIN");

//         UserDao user = new UserDao();
//         user.setUsername("moderator");
//         user.setRoles(adminRole);
//         userRepository.save(user);
//     }

//     @Transactional
//     Privilege createPrivilege(String name) {
//         Privilege privilege = new Privilege();
//         privilege.setName(name);
//         privilegeRepository.save(privilege);
//         return privilege;
//     }

//     @Transactional
//     Role createRole(String name, Collection<Privilege> privileges) {
//         Role role = new Role();
//         role.setName(name);
//         role.setPrivileges(privileges);
//         roleRepository.save(role);
//         return role;
//     }
// }