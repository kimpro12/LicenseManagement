package com.LicenseManagement.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails kim = User.builder()
                .username("kim")
                .password("{noop}kimpro")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(kim);
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.POST, "/api/v1/licenses").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/licenses").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/licenses/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/licenses").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/licenses").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/licenses/{id}").hasRole("ADMIN"));

        http.httpBasic(Customizer.withDefaults());
        http.csrf(csrf -> csrf.disable());
        return http.build();
    }
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(HttpMethod.GET, "/api/v1/licenses/verify");
    }
}
