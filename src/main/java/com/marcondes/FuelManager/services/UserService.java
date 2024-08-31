package com.marcondes.FuelManager.services;

import com.marcondes.FuelManager.entities.User;
import com.marcondes.FuelManager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void createUser(User user) {
        userRepository.save(user);
    }
}
