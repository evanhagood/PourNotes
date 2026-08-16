package com.evanhagood.pournotes.auth;

import com.evanhagood.pournotes.user.AppUserService;

import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
     * On prod, gets the authenticated oidcUser. On dev, gets the UserDetails of preset accounts.
     *
     * @param AuthenticationPrincipal authenticated user entity provided by Spring Security
     * @return current PourNotes user information
     */
    @GetMapping("/me")
    public CurrentUserResponse getCurrentUser(@AuthenticationPrincipal Object principal) {
        return appUserService.getCurrentUser(principal);
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