package org.pl.deenes.services;


import lombok.AllArgsConstructor;
import org.pl.deenes.infrastructure.repositories.DispatcherRepository;
import org.pl.deenes.infrastructure.repositories.DriverRepository;
import org.pl.deenes.services.interfaces.LoginService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final DispatcherRepository dispatcherRepository;

    private final DriverRepository driverRepository;

    //TODO: add drivers to users

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String surname) throws UsernameNotFoundException {
        var dispatcher = dispatcherRepository.findBySurname(surname)
                .orElseThrow(() -> new UsernameNotFoundException("user not found: %s".formatted(surname)));

        List<SimpleGrantedAuthority> list = dispatcher.getRoles().stream()
                .map(r -> new SimpleGrantedAuthority(r.getRole())).distinct().toList();

        return new User(
                dispatcher.getSurname(),
                dispatcher.getPassword(),
                dispatcher.getActive(),
                true, true, true, list);
    }
}
