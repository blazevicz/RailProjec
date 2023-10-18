package org.pl.deenes.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.pl.deenes.infrastructure.entity.LineEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Data
@Builder
@EqualsAndHashCode
public class Driver implements User, UserDetails {

    private Integer driverId;
    private String name;
    private String surname;
    private String pesel;

    private Set<LocomotiveType> locomotiveAuthorization;
    private Set<LineEntity> lineAuthorization;
    private Set<Train> trains;

    private Boolean active;
    private Set<Role> roles;
    //private Set<RoleEntity> roles;
    private String password;

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
