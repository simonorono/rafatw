DROP TABLE IF EXISTS usr CASCADE;
CREATE TABLE usr (
    id                BIGINT PRIMARY KEY,
    created_at        TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    description       TEXT,
    followers_count   INTEGER NOT NULL,
    friends_count     INTEGER NOT NULL,
    lang              TEXT NOT NULL,
    location          TEXT,
    name              TEXT NOT NULL,
    profile_image_url TEXT NOT NULL,
    protected         BOOLEAN NOT NULL,
    screen_name       TEXT UNIQUE NOT NULL,
    statuses_count    INTEGER NOT NULL,
    timezone          TEXT,
    url               TEXT,
    verified          BOOLEAN NOT NULL
);

DROP TABLE IF EXISTS tweet CASCADE;
CREATE TABLE tweet (
    id                      BIGINT PRIMARY KEY,
    longitude               DOUBLE PRECISION,
    latitude                DOUBLE PRECISION,
    created_at              TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    in_reply_to_status_id   BIGINT,
    in_reply_to_user_id     BIGINT,
    lang                    TEXT,
    source                  TEXT NOT NULL,
    twtext                  TEXT NOT NULL,
    user_id                 BIGINT NOT NULL REFERENCES usr (id) ON DELETE CASCADE ON UPDATE CASCADE
);