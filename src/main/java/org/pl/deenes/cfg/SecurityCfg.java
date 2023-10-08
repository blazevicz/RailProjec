package org.pl.deenes.cfg;


import lombok.NonNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity(debug = true)
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
@Configuration
public class SecurityCfg {
    private static final String DISPATCHER = "DISPATCHER";
    private static final String DRIVER = "DRIVER";
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authManager(
            @NonNull HttpSecurity http,
            PasswordEncoder passwordEncoder,
            UserDetailsService userDetailService
    ) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailService)
                .passwordEncoder(passwordEncoder)
                .and()
                .build();
    }

    @Bean
    public SecurityFilterChain filterChain(@NonNull HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .requestMatchers("/login", "/error").permitAll()
                .requestMatchers(HttpMethod.DELETE).hasAuthority(DISPATCHER)
                .requestMatchers("/warnings").hasAnyAuthority(DRIVER, DISPATCHER)
                .requestMatchers("/").hasAnyAuthority(DISPATCHER, DRIVER)
                .requestMatchers("/drivers").hasAnyAuthority(DISPATCHER, DRIVER)
                .requestMatchers("/dispatcher").hasAnyAuthority(DISPATCHER)
                .requestMatchers("/trains/**").hasAnyAuthority(DISPATCHER, DRIVER)
                .requestMatchers(HttpMethod.POST, "/api/train/analyse").permitAll()
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").hasAnyAuthority(DISPATCHER, DRIVER)
                .requestMatchers("/api/**").permitAll()
                // .requestMatchers("/*").hasAnyAuthority(DISPATCHER)
                .and()
                .formLogin()
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll();
        return http.build();
    }
}