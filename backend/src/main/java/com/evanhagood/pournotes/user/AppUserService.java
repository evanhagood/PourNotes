package com.evanhagood.pournotes.user;

import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.evanhagood.pournotes.auth.CurrentUserResponse;

@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;

    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Transactional
    public AppUser findOrCreateUser(
            String subject,
            String displayName
    ) {
        return appUserRepository
                .findBySubject(subject)
                .orElseGet(() -> {
                    AppUser newUser = new AppUser(
                            subject,
                            displayName
                    );

                    return appUserRepository.save(newUser);
                });
    }

    @Transactional(readOnly = true)
    public AppUser getBySubject(String subject) {
        return appUserRepository
                .findBySubject(subject)
                .orElseThrow(() ->
                        new IllegalStateException(
                                "Authenticated user has no local account"
                        )
                );
    }

    @Transactional(readOnly = true)
    public CurrentUserResponse getCurrentUser(OidcUser oidcUser) {
        AppUser appUser = appUserRepository
                .findBySubject(oidcUser.getSubject())
                .orElseThrow(() -> new AppUserNotFoundException(oidcUser.getEmail()));

        return new CurrentUserResponse(
                appUser.getId(),
                appUser.getDisplayName(),
                oidcUser.getEmail(),
                Boolean.TRUE.equals(oidcUser.getEmailVerified()),
                oidcUser.getPicture(),
                appUser.getRole()
        );
    }
}