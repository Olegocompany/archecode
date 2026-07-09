package com.free.archecode.app.user;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;


public class UserDetails implements org.springframework.security.core.userdetails.UserDetails, CredentialsContainer {

    private final User user;


    public UserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(
                new SimpleGrantedAuthority("ROLE_" + user.getRole().toUpperCase())
        );
    }

    @Override
    public String getPassword() {
        return user.getPassword(); //хэш
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public void eraseCredentials() {
        this.user.setPassword(null);
    }

}
