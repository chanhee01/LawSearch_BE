package com.example.lawSearch.global.config.auth;

import com.example.lawSearch.domain.user.repository.UserRepository;
import com.example.lawSearch.global.auth.PrincipalDetailsService;
import com.example.lawSearch.global.auth.jwt.JwtAuthenticationFilter;
import com.example.lawSearch.global.auth.jwt.JwtAuthorizationFilter;
import com.example.lawSearch.global.auth.jwt.JwtProperties;
import com.example.lawSearch.global.config.web.CorsConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

    private final CorsConfig corsConfig;
    private final UserRepository userRepository;
    private final PrincipalDetailsService userDetailsService;
    private final JwtProperties jwtProperties;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(CsrfConfigurer::disable);
        http.sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.formLogin(formLogin -> formLogin.disable());

        AuthenticationManagerBuilder sharedObject = http.getSharedObject(AuthenticationManagerBuilder.class);

        sharedObject.userDetailsService(this.userDetailsService);
        AuthenticationManager authenticationManager = sharedObject.build();

        http.authenticationManager(authenticationManager);

        http.addFilterBefore(corsConfig.corsFilter(), SecurityContextPersistenceFilter.class);
        http.addFilterBefore(new JwtAuthenticationFilter(authenticationManager, jwtProperties), UsernamePasswordAuthenticationFilter.class);
        http.addFilter(new JwtAuthorizationFilter(authenticationManager, userRepository, jwtProperties));

        http.httpBasic(httpBasic ->
                httpBasic.disable()
        );

        http.authorizeHttpRequests(authorize ->
                authorize
                        .requestMatchers("/api/user/**").permitAll()
                        .requestMatchers("/api/**").authenticated()
                        .requestMatchers("/api/answer/**").hasAnyRole("ADMIN")

                        .anyRequest().permitAll()
        );

        return http.build();
    }
}
