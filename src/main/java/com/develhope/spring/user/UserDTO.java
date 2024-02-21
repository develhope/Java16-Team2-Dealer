package com.develhope.spring.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.Super;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UserDTO {

    private Long id;

    private String nome;

    private String cognome;

    private String numeroTelefono;

    private String email;

    private String password;

    private UserRole role;

    public UserEntity toEntity() {
        return new UserEntity(this.id, this.nome, this.cognome, this.numeroTelefono, this. email, this.password,
                this.role);
    }
}
