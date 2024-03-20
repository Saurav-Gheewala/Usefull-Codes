package com.parectice.SpringJWT.service;

import com.parectice.SpringJWT.model.User;
import com.parectice.SpringJWT.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    public User addUsers(User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public ResponseEntity<List<User>> showUsers() {
        return new ResponseEntity<>(userRepository.findAll(),HttpStatus.OK);
    }
}
