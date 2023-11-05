package org.pl.deenes.model;

import lombok.Builder;
import lombok.Data;
import org.pl.deenes.infrastructure.entity.RoleEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Builder
@Data
public class Dispatcher implements UserDetails {

    private Integer id;
    private String name;
    private String surname;
    private String phoneNumber;
    private Boolean active;
    private Set<RoleEntity> roles;
    private String password;
    private Long pesel;
    private List<Train> trains;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole()))
                .toList();
    }


    @Override
    public String getUsername() {
        return surname;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
