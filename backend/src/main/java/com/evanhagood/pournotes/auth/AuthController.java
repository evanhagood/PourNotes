package com.evanhagood.pournotes.auth;

import com.evanhagood.pournotes.user.AppUserService;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Provides information about the currently authenticated user.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AppUserService appUserService;

    public AuthController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    /**
     * Returns the current user's PourNotes account information.
     *
     * @param oidcUser authenticated Google principal supplied by Spring Security
     * @return current PourNotes user information
     */
    @GetMapping("/me")
    public CurrentUserResponse getCurrentUser(@AuthenticationPrincipal OidcUser oidcUser) {
        return appUserService.getCurrentUser(oidcUser);
    }

    /**
     * Returns the CSRF token associated with the current HTTP session.
     *
     * @param csrfToken CSRF token supplied by Spring Security
     * @return current session's CSRF token
     */
    @GetMapping("/csrf")
    public CsrfToken getCSRFToken(CsrfToken token) {
        return token;
    }
}