create table if not exists doctors
(
    id bigserial primary key,
    firstname text not null,
    lastname text not null,
    email text not null,
    phone text not null,
    qualification text not null,
    dateJoined date not null,
    departmentId bigint not null references departments(id),
    isActive boolean not null,
    createdAt timestamp not null
);