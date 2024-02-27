package com.develhope.spring.features.user.repository;

import com.develhope.spring.features.user.UserService;
import com.develhope.spring.features.user.entities.Role;
import com.develhope.spring.features.user.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);

    List<UserEntity> findByRole(Role role);

}
