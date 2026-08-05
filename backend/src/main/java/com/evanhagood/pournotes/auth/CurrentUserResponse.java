package com.evanhagood.pournotes.auth;

import java.util.UUID;

import com.evanhagood.pournotes.user.UserRole;

/**
 * Represents the authenticated user's account information exposed to the
 * PourNotes frontend.
 *
 * @param id internal PourNotes account identifier
 * @param displayName name displayed inside PourNotes
 * @param email email supplied by the authenticated Google identity
 * @param emailVerified whether Google reports that the email is verified
 * @param pictureUrl URL of the user's Google profile picture
 * @param role user's authorization role within PourNotes
 */
public record CurrentUserResponse(
        UUID id,
        String displayName,
        String email,
        boolean emailVerified,
        String pictureUrl,
        UserRole role
) {
}