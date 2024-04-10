package org.cris6h16.springsecurity_authorizehttprequests.SecurityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthorizationManagers;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;

import static jakarta.servlet.DispatcherType.*;

import static org.springframework.security.authorization.AuthorizationManagers.allOf;
import static org.springframework.security.authorization.AuthorityAuthorizationManager.hasAuthority;
import static org.springframework.security.authorization.AuthorityAuthorizationManager.hasRole;

@Configuration
@EnableWebSecurity
public class MoreAboutConfig {

    @Bean
    SecurityFilterChain web(HttpSecurity http) throws Exception {
        http
                // ...
                .authorizeHttpRequests(authorize -> authorize
                        .dispatcherTypeMatchers(FORWARD, ERROR).permitAll() // allow Spring MVC to render views(FORWARD) and Spring Boot to render errors(ERROR)
                        .requestMatchers("/static/**", "/signup", "/about").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN") // authority: <ROLE_>ADMIN
                        .requestMatchers("/db/**").access(AuthorizationManagers.allOf(hasAuthority("db"), hasRole("ADMIN")))
                        .requestMatchers("/test/**").access(new WebExpressionAuthorizationManager("hasRole('ADMIN') && hasRole('USER')"))
                        .anyRequest().denyAll()
                );

        return http.build();
    }
}
