package org.pl.deenes.cfg;


import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity(debug = true)
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
@Configuration
@RequiredArgsConstructor
@Slf4j
public class SecurityCfg {
    private static final String[] WHITE_LIST_URL = {
            "/api/auth/**",
            "/swagger-resources",
            "/swagger-resources/**",
            //    "/swagger-ui/**",
            "/api/register",
            "/warnings/**",
            "/static/**",
            "/popper.min.js",
            "/error",
            "/styles/**",
            "/",
            "/assets/**",
            "/css/**",
            "/js/**",
            "/static/assets/**",
            "/static/css/**",
            "/static/js/**",
            "/images/**",
            "/fonts/**",
            "/templates/**",
            "/fragments/**",
            //  "/swagger-ui.html"
    };
    private static final String DISPATCHER = "DISPATCHER";
    private static final String DRIVER = "DRIVER";

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public AuthenticationManager authManager(
            @NonNull HttpSecurity http,
            PasswordEncoder passwordEncoder,
            @Qualifier("userDetailsService") UserDetailsService userDetailService
    ) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailService)
                .passwordEncoder(passwordEncoder)
                .and()
                .build();
    }

    @Bean
    public SecurityFilterChain filterChain(@NonNull HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests(a -> a
                        .requestMatchers(WHITE_LIST_URL).permitAll()
                        .requestMatchers("/login", "/error", "/api/auth/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE).hasAuthority(DISPATCHER)
                        .requestMatchers("/drivers").hasAnyAuthority(DISPATCHER, DRIVER)
                        .requestMatchers("/dispatcher").hasAnyAuthority(DISPATCHER)
                        .requestMatchers("/trains/**").hasAnyAuthority(DISPATCHER, DRIVER)
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").hasAnyAuthority(DISPATCHER, DRIVER)
                        .requestMatchers("/api/**").authenticated()
                )
                .formLogin().permitAll()
                .and()
                .logout().logoutSuccessUrl("/login")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID").permitAll()
                .and()
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}