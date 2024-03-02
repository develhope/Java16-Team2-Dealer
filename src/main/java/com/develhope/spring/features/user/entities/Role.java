package com.develhope.spring.features.user.entities;


import lombok.Getter;


@Getter
public enum Role {

    ADMIN(1000),
    SELLER(500),
    CUSTOMER(100),
    NOT_SET(0);

    public final Integer authorityLevel;

    private Role(Integer authorityLevel) {
        this.authorityLevel = authorityLevel;
    }


}
