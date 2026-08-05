CREATE TABLE app_users (
    id UUID PRIMARY KEY,

    subject VARCHAR(255) NOT NULL,

    display_name VARCHAR(100) NOT NULL,

    role VARCHAR(20) NOT NULL,

    created_at TIMESTAMPTZ NOT NULL,

    updated_at TIMESTAMPTZ NOT NULL,

    CONSTRAINT uk_app_users_google_subject
        UNIQUE (subject),

    CONSTRAINT ck_app_users_display_name
        CHECK (length(trim(display_name)) > 0),

    CONSTRAINT ck_app_users_role
        CHECK (role IN ('USER', 'ADMIN'))
);