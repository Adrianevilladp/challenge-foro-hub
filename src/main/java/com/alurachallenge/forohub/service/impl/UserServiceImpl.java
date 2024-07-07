package com.alurachallenge.forohub.service.impl;

import com.alurachallenge.forohub.constant.MessageConstant;
import com.alurachallenge.forohub.entity.Profile;
import com.alurachallenge.forohub.entity.User;
import com.alurachallenge.forohub.exception.NotFoundEntityException;
import com.alurachallenge.forohub.payload.RegisterRequest;
import com.alurachallenge.forohub.repository.ProfileRepository;
import com.alurachallenge.forohub.repository.UserRepository;
import com.alurachallenge.forohub.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private PasswordEncoder passwordEncoder;
    private ProfileRepository profileRepository;
    private UserRepository userRepository;


    @Override
    @Transactional
    public User create(RegisterRequest registerRequest) {
        User userToSave = new User();
        userToSave.setName(registerRequest.name());
        userToSave.setEmail(registerRequest.email());
        userToSave.setPassword(passwordEncoder.encode(registerRequest.password()));
        assignDefaultProfileToUser(userToSave);

        return userRepository.save(userToSave);
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundEntityException(MessageConstant
                        .USER_NOT_FOUND
                        .formatted(MessageConstant.USER_ID_NOT_FOUND)));
    }

    private void assignDefaultProfileToUser(User user) {
        Profile profile = profileRepository.findByName(MessageConstant.DEFAULT_USER_PROFILE);
        profile.addUser(user);
    }
}
