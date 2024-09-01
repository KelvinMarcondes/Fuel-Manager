package com.marcondes.FuelManager.security;

import com.marcondes.FuelManager.entities.User;
import com.marcondes.FuelManager.repositories.RoleRepository;
import com.marcondes.FuelManager.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

@Configuration
public class AdminUserConfig implements CommandLineRunner{

    private RoleRepository roleRepository;

    private UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AdminUserConfig(RoleRepository roleRepository, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        var roleAdmin = roleRepository.findByName("admin");
        var userAdmin = userRepository.findUserByEmail("admin@email.com");

        if (userAdmin != null) {
            System.out.println("email de admin já existe.");
        } else {
            var user = new User();
            user.setName("admin");
            user.setEmail("admin@email.com");
            user.setRoles(Set.of(roleAdmin));
            user.setPassword(bCryptPasswordEncoder.encode("123"));
            userRepository.save(user);
        }

    }
}