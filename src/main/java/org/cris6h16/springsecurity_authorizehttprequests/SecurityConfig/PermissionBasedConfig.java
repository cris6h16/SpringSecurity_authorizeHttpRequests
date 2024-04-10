package org.cris6h16.springsecurity_authorizehttprequests.SecurityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;

//@Configuration
//@EnableWebSecurity
public class PermissionBasedConfig {

    @Bean
    SecurityFilterChain web(HttpSecurity http) throws Exception {
        WebExpressionAuthorizationManager expression =
                new WebExpressionAuthorizationManager("#name == authentication.name");

        http
                .authorizeHttpRequests((authorize) -> authorize
                        /*
                        if the request is a GET, then require read permission; else, if the request is a POST, then require write permission; else, deny the request
                         */
                            .requestMatchers(HttpMethod.GET, "/resource/**").hasAuthority("read")
                            .requestMatchers(HttpMethod.POST, "/resource/**").hasAuthority("write")
                            .anyRequest().denyAll() //healthy security practice
                );
        // ...

        return http.build();
    }

}
