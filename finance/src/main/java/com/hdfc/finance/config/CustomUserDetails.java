package com.hdfc.finance.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.hdfc.finance.model.User;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

    private static final long serialVersionUID = 1L;
    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String role = user.getRole();
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    public Long getUserId() {
        return user.getUserID();
    }

    public String getRole() {
        return user.getRole();
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO: Implement account expiration logic
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO: Implement account locking logic
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO: Implement credentials expiration logic
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO: Implement user activation/deactivation logic
        return true;
    }
}
