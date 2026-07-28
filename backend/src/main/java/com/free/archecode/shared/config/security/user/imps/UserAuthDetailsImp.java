package com.free.archecode.shared.config.security.user.imps;

import com.free.archecode.shared.config.security.user.UserAuthDetails;
import com.free.archecode.user.User;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserAuthDetailsImp implements UserDetails, CredentialsContainer, UserAuthDetails {

    private final User user;

    public UserAuthDetailsImp(User user) {
        this.user = user;
    }

    @Override
    @NullMarked
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(
                new SimpleGrantedAuthority("ROLE_" + user.getRole().toUpperCase())
        );
    }

    /**
     * @return hash of password, not password itself.
     */
    @Override
    public String getPassword() {
        return user.getPassword();
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
