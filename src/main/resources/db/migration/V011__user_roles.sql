create table if not exists user_roles
(
    id bigserial primary key,
    userId bigint not null references users(id),
    roleId bigint not null references roles(id)
);