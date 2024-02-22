package com.develhope.spring.admins;

import com.develhope.spring.user.UserEntity;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class AdminEntity extends UserEntity {
}
