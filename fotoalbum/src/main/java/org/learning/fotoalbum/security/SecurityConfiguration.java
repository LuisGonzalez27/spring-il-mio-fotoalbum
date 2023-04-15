package org.learning.fotoalbum.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
    @Bean
    UserDetailsService userDetailsService() {
        return new DatabaseUserDetailsService();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    /*
     * home "/" USER, ADMIN
     * photos "/photos" USER, ADMIN
     * create/edit "/photos/create" , "/photos/edit/{id}" ADMIN
     * show "photos/{id)" USER, ADMIN
     * categories "/categories/..." ADMIN
     */
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .requestMatchers("/categories/create", "/categories/save/**", "/categories/delete/**").hasAuthority("ADMIN")
                .requestMatchers("/photos/create", "/photos/edit/**", "/photos/delete/**")
                .hasAnyAuthority("ADMIN")
                .requestMatchers("/photos", "/photos/**", "/categories")
                .hasAnyAuthority("ADMIN", "USER")
                .requestMatchers(HttpMethod.POST, "/photos/**").hasAnyAuthority("ADMIN")
                .requestMatchers("/**").permitAll()
                .and().formLogin()
                .and().logout()
                .and().exceptionHandling();
        return http.build();
    }
}
