package org.cris6h16.springsecurity_authorizehttprequests.SecurityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain web(HttpSecurity http) throws Exception {
        WebExpressionAuthorizationManager expression =
                new WebExpressionAuthorizationManager("#name == authentication.name");

        http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/auth").hasAuthority("USER")
                        .requestMatchers("/auth/{name}").access(expression)
                        .requestMatchers("/public/**").permitAll()
                        .anyRequest().authenticated()
                        Matching Using Regular Expressions
                );
        // ...

        return http.build();
    }

}
