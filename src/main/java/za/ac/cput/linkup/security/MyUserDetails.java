package za.ac.cput.linkup.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import za.ac.cput.linkup.domain.Admin;
import za.ac.cput.linkup.domain.User;

import java.util.Collection;
import java.util.List;

public class MyUserDetails implements UserDetails {

    private final String username;
    private final String password;
    private final String role;

    // One constructor for both types
    public MyUserDetails(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.role = user.getRole().name();
    }

    public MyUserDetails(Admin admin) {
        this.username = admin.getUsername();
        this.password = admin.getPassword();
        this.role = admin.getRole().name();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role));
    }

    @Override
    public String getPassword() { return password; }

    @Override
    public String getUsername() { return username; }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
}
