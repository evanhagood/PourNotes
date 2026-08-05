package com.evanhagood.pournotes.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                .findByGoogleSubject(subject)
                .orElseGet(() -> {
                    AppUser newUser = new AppUser(
                            subject,
                            displayName
                    );

                    return appUserRepository.save(newUser);
                });
    }

    @Transactional(readOnly = true)
    public AppUser getByGoogleSubject(String subject) {
        return appUserRepository
                .findByGoogleSubject(subject)
                .orElseThrow(() ->
                        new IllegalStateException(
                                "Authenticated user has no local account"
                        )
                );
    }
}