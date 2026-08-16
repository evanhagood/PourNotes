package com.evanhagood.pournotes.auth.dev;

import com.evanhagood.pournotes.user.AppUserService;
import com.evanhagood.pournotes.user.UserRole;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/*
/ Full honesty this is whatever chatgpt jank came out of me asking it to do this for me
/ i didnt want to set the db manually, so here we are. this, at least, will not run in prod.
*/

@Component
@Profile("dev")
public class DevUserSeeder implements ApplicationRunner {

    private final AppUserService appUserService;

    public DevUserSeeder(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @Override
    public void run(ApplicationArguments args) {
        appUserService.findOrCreateUser(
                "dev:dev",
                "Dev User",
                UserRole.USER
        );

        appUserService.findOrCreateUser(
                "dev:admin",
                "Dev Admin",
                UserRole.ADMIN
        );
    }
}