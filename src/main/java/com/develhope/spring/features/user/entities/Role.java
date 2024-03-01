package com.develhope.spring.features.user.entities;

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
