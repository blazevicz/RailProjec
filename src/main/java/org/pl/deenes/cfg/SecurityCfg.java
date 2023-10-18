package org.pl.deenes.cfg;


import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
public class SecurityCfg {

    private static final String[] WHITE_LIST_URL = {
            //   "/api/**",
            "/api/auth/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/api/register",
            "/swagger-ui.html"};
    private static final String DISPATCHER = "DISPATCHER";
    private static final String DRIVER = "DRIVER";

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    // private final LogoutHandler logoutHandler;

 /*   @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }*/

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
        http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/api/auth/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);



       /* http.csrf().disable()
                .authorizeHttpRequests(req ->
                        req.requestMatchers(WHITE_LIST_URL)
                                .permitAll()
                                .requestMatchers("/api/register").hasAnyRole(DISPATCHER, DRIVER)
                                .requestMatchers(GET, "/api/test").hasAnyRole(DISPATCHER, DRIVER)
                                .requestMatchers(GET, "/api/v1/management/**").hasAnyAuthority(DISPATCHER, DRIVER)
                                .requestMatchers(POST, "/api/v1/management/**").hasAnyAuthority(DISPATCHER, DRIVER)
                                .requestMatchers(PUT, "/api/v1/management/**").hasAnyAuthority(DISPATCHER, DRIVER)
                                .requestMatchers(DELETE, "/api/v1/management/**").hasAnyAuthority(DISPATCHER, DRIVER)
                                .anyRequest()
                                .authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout ->
                        logout.logoutUrl("/api/auth/logout")
                             //   .addLogoutHandler(logoutHandler)
                                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
                );*/
        return http.build();
    }
 /*   @Bean
    public SecurityFilterChain filterChain(@NonNull HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests(r -> r.requestMatchers("/api/**").permitAll())
                .authorizeHttpRequests(a-> {
                    try {
                        a.anyRequest().authenticated()
                        //.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
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
                        .and()
                        .formLogin()
                        .permitAll()
                        .and()
                        .logout()
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
        return http.build();
    }*/
}