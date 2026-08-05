package com.evanhagood.pournotes.auth.oidc;

import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import com.evanhagood.pournotes.user.AppUserService;

@Service
public class PourNotesOidcUserService implements OAuth2UserService<OidcUserRequest, OidcUser> {
    
    private final OidcUserService delegate;
    private final AppUserService appUserService;

    public PourNotesOidcUserService(AppUserService appUserService) {
        this.appUserService = appUserService;
        this.delegate = new OidcUserService();
    }

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest)
        throws OAuth2AuthenticationException {
        
        OidcUser oidcUser = delegate.loadUser(userRequest);

        String subject = oidcUser.getSubject();
        String email = oidcUser.getEmail();

        appUserService.findOrCreateUser(subject, email);

        return oidcUser;
    }
}
