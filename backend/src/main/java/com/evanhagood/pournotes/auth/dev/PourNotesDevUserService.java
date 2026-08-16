package com.evanhagood.pournotes.auth.dev;

import com.evanhagood.pournotes.user.AppUserService;
import com.evanhagood.pournotes.user.UserRole;

import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class PourNotesDevUserService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        // in replacement of a Repository since these do not need to be in the DB
        // and, also, kinda makes it conceptually easier than inventing a DevUserRepository class that really doesn't provide us anything
        // more than whats here in this one switch statement.
        return switch (username) {
            case "dev" -> User.withUsername("dev")
                    .password("{noop}dev")
                    .roles(UserRole.USER.name())
                    .build();

            case "admin" -> User.withUsername("admin")
                    .password("{noop}admin")
                    .roles(UserRole.ADMIN.name())
                    .build();

            default -> throw new UsernameNotFoundException(
                    "Unknown dev user: " + username
            );
        };
    }
}