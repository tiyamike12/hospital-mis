create table if not exists roles
(
    id bigserial primary key,
    name text not null
);

INSERT INTO roles (name)
VALUES
('system-administrator'),
('reception'),
('hospital-director');
