package com.exam.examPortal.model;

import org.springframework.security.core.GrantedAuthority;

public class Autherity implements GrantedAuthority {
    private String authority;

    public Autherity(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
