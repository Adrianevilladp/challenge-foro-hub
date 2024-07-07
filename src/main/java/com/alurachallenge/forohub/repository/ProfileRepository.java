package com.alurachallenge.forohub.repository;

import com.alurachallenge.forohub.entity.Profile;
import com.alurachallenge.forohub.enums.EProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Profile findByName(EProfile defaultUserProfile);
}
