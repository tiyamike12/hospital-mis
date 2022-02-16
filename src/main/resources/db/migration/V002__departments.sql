create table if not exists departments
(
    id bigserial primary key,
    code text not null,
    name text not null
);