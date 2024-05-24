package com.group06.JobFairApp.service;

import com.group06.JobFairApp.model.Users;
import com.group06.JobFairApp.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UsersRepository userRepository;
//    private final PasswordEncoder passwordEncoder;

//    @Autowired
//    public UserService(UsersRepository userRepository, PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
    @Autowired
    public UserService(UsersRepository userRepository) {
        this.userRepository = userRepository;
    }

//    public Users createUser(String email, String password, String name, String surname, String phoneNumber, String roles) {
//        Users user = new Users(email, passwordEncoder.encode(password), name, surname, phoneNumber, roles);
//        return userRepository.save(user);
//    }

    public Users createUser(String email, String password, String name, String surname, String phoneNumber, String roles) {
        Users user = new Users(email, password, name, surname, phoneNumber, roles);
        return userRepository.save(user);
    }

    public Users findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

//    public boolean loginUser(String email, String password) {
//        Users user = userRepository.findByEmail(email);
//        return user != null && passwordEncoder.matches(password, user.getPassword());
//    }

    public boolean loginUser(String email, String password) {
        Users user = userRepository.findByEmail(email);
        return user != null && user.getPassword().equals(password);
    }
}
