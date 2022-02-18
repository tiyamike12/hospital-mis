create table if not exists lab_results
(
    id bigserial primary key,
    testName text not null,
    testDate date not null,
    conductedBy text not null,
    patientId bigint not null references patients(id),
    result boolean not null,
    createdAt timestamp not null
);