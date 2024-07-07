package com.alurachallenge.forohub.service.impl;

import com.alurachallenge.forohub.constant.MessageConstant;
import com.alurachallenge.forohub.entity.Profile;
import com.alurachallenge.forohub.entity.User;
import com.alurachallenge.forohub.payload.MessageResponse;
import com.alurachallenge.forohub.repository.ProfileRepository;
import com.alurachallenge.forohub.service.AdminService;
import com.alurachallenge.forohub.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class AdminServiceImpl implements AdminService {
    private UserService userService;
    private ProfileRepository profileRepository;

    @Override
    @Transactional
    public MessageResponse giveAdminRolToUser(Long id) {
        User userFound = userService.getById(id);
        assignAdminProfileToUser(userFound);

        return new MessageResponse(MessageConstant
                .NEW_ADMIN_USER
                .formatted(userFound.getName()));
    }

    private void assignAdminProfileToUser(User user) {
        Profile profile = profileRepository.findByName(MessageConstant.ADMIN_USER_PROFILE);
        if (!profile.getUsers().contains(user)) {
            profile.addUser(user);
        }
    }

}
