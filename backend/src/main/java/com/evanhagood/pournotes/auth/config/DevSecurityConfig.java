package com.evanhagood.pournotes.auth.config;

import static org.springframework.security.web.servlet.util.matcher
        .PathPatternRequestMatcher.pathPattern;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@Profile("dev")
@EnableWebSecurity
public class DevSecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        // its fine to leave passwords unencrypted here. this only runs locally.
        UserDetails devUser = User.withUsername("dev")
                .password("{noop}dev")
                .roles("USER")
                .build();

        UserDetails devAdmin = User.withUsername("admin")
            .password("{noop}admin")
            .roles("ADMIN")
            .build();

        return new InMemoryUserDetailsManager(devUser, devAdmin);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(withDefaults())

                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/auth/csrf").permitAll()

                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/drinks/**"
                        ).permitAll()

                        .anyRequest().authenticated()
                )

                .exceptionHandling(exceptions -> exceptions
                        .defaultAuthenticationEntryPointFor(
                                new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED),
                                pathPattern("/api/**")
                        )
                )

                .formLogin(form -> form
                        .loginProcessingUrl("/api/auth/dev-login")
                        .successHandler((request, response, authentication) ->
                                response.setStatus(HttpStatus.NO_CONTENT.value())
                        )
                        .failureHandler((request, response, exception) ->
                                response.setStatus(HttpStatus.UNAUTHORIZED.value())
                        )
                )

                .logout(logout -> logout
                        .logoutUrl("/api/auth/logout")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID")
                        .logoutSuccessHandler(
                                (request, response, authentication) ->
                                        response.setStatus(HttpStatus.NO_CONTENT.value())
                        )
                )

                .csrf(csrf -> csrf
                        .csrfTokenRepository(new HttpSessionCsrfTokenRepository())
                );

        return http.build();
    }
}