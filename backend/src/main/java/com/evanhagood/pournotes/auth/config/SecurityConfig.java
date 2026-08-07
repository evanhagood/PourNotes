package com.evanhagood.pournotes.auth.config;

import static org.springframework.security.web.servlet.util.matcher
        .PathPatternRequestMatcher.pathPattern;

import com.evanhagood.pournotes.auth.oidc.PourNotesOidcUserService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Value("${app.frontend-url}")
    private String frontendUrl;

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            PourNotesOidcUserService pourNotesOidcUserService
    ) throws Exception {

        AuthenticationEntryPoint apiAuthenticationEntryPoint =
                new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED);

        http
                .cors(withDefaults())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/auth/csrf").permitAll()

                        .requestMatchers(
                                "/oauth2/**",
                                "/login/**",
                                "/error"
                        ).permitAll()

                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/drinks/**"
                        ).permitAll()

                        .anyRequest().authenticated()
                )

                // Auth related exceptions will send a 401 so the frontend can reprompt the user to log in
                .exceptionHandling(exceptions -> exceptions
                        .defaultAuthenticationEntryPointFor(
                                apiAuthenticationEntryPoint,
                                pathPattern("/api/**")
                        )
                )

                .oauth2Login(oauth -> oauth
                        .userInfoEndpoint(userInfo -> userInfo
                                .oidcUserService(pourNotesOidcUserService)
                        )
                        .defaultSuccessUrl(
                                frontendUrl + "/account",
                                true
                        )
                )

                .logout(logout -> logout
                        .logoutUrl("/api/auth/logout")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID")
                        .logoutSuccessHandler(
                                (request, response, authentication) ->
                                        response.setStatus(
                                                HttpStatus.NO_CONTENT.value()
                                        )
                        )
                );

        // Do not call csrf.disable().
        return http.build();
    }
}