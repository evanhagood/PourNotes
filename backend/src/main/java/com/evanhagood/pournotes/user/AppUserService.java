package com.evanhagood.pournotes.user;

import com.evanhagood.pournotes.auth.oidc.PourNotesOidcUserService;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
            String displayName,
            UserRole role
    ) {
        return appUserRepository
                .findBySubject(subject)
                .orElseGet(() -> {
                    AppUser newUser = new AppUser(
                            subject,
                            displayName,
                            role
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
    public CurrentUserResponse getCurrentUser(Object principal) {
        if (principal instanceof OidcUser oidcUser) {
            AppUser appUser = appUserRepository
                    .findBySubject(oidcUser.getSubject())
                    .orElseThrow(() ->
                            new AppUserNotFoundException(
                                    oidcUser.getEmail()
                            )
                    );

            return new CurrentUserResponse(
                    appUser.getId(),
                    appUser.getDisplayName(),
                    oidcUser.getEmail(),
                    Boolean.TRUE.equals(oidcUser.getEmailVerified()),
                    oidcUser.getPicture(),
                    appUser.getRole()
            );
        }

        if (principal instanceof UserDetails userDetails) {
                String subject = "dev:" + userDetails.getUsername();

                AppUser appUser = appUserRepository
                .findBySubject(subject)
                .orElseThrow(() ->
                        new AppUserNotFoundException(userDetails.getUsername())
                );

                return new CurrentUserResponse(
                        appUser.getId(),
                        appUser.getDisplayName(),
                        userDetails.getUsername() + "@dev.local",
                        true,
                        null,
                        appUser.getRole()
                );
        }

        throw new IllegalArgumentException("Principal of type " + principal.getClass() + " is unknown.");
}
}