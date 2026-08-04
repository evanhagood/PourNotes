package com.evanhagood.pournotes.user;

import java.time.Instant;
import java.util.UUID;

/**
 * Represents PourNotes profile information returned to the client.
 *
 * @param id internal PourNotes account identifier
 * @param displayName name displayed inside PourNotes
 * @param role user's PourNotes authorization role
 * @param createdAt time at which the account was created
 */
public record AppUserResponse(
        UUID id,
        String displayName,
        UserRole role,
        Instant createdAt
) {

    public static AppUserResponse from(AppUser user) {
        return new AppUserResponse(
                user.getId(),
                user.getDisplayName(),
                user.getRole(),
                user.getCreatedAt()
        );
    }
}