create table if not exists patients
(
    id bigserial primary key,
    firstname text not null,
    lastname text not null,
    gender text not null,
    dateofbirth date not null,
    phone text not null,
    email text not null,
    address text not null,
    createdAt timestamp not null
);