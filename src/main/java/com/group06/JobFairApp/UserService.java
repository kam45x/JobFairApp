package com.group06.JobFairApp;

import com.group06.JobFairApp.model.Users;
import com.group06.JobFairApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Users createUser(String email, String password, String name, String surname, String phoneNumber, String roles) {
        Users user = new Users(email, password, name, surname, phoneNumber, roles);
        return userRepository.save(user);
    }

    public Users findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
