create table if not exists users
(
    id bigserial primary key,
    firstname text not null,
    lastname text not null,
    username text not null,
    email text not null,
    password text not null
);

INSERT INTO users (email, password, username, firstname, lastname)
VALUES ('tiyamikenkhono@gmail.com', '$2a$10$UCebcUXL.sWnK3Igf9NQY.skdJQ20K1J0sBCdJnX7/geOiWRWKwLW',
        'tnkhono', 'Tiyamike', 'Nkhono');