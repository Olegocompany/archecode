package com.free.archecode.shared.config.security.user;

import com.free.archecode.user.User;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;

public class ImpUserAuthDetails implements org.springframework.security.core.userdetails.UserDetails, CredentialsContainer {

    private final User user;

    public ImpUserAuthDetails(User user) {
        this.user = user;
    }

    @Override
    @NullMarked
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
    @NullMarked
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public void eraseCredentials() {
        this.user.setPassword(null);
    }

    public Long getUserId() {
        return user.getId();
    }

    public User getUser() {return user;}

}
