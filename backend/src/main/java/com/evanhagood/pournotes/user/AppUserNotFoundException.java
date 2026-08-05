package com.evanhagood.pournotes.user;

public class AppUserNotFoundException extends RuntimeException {
    public AppUserNotFoundException(String email) {
        super("The user " + email + " could not be found.");
    }
}
