create table if not exists patient_cases
(
    id bigserial primary key,
    diagnosis text not null,
    caseDate date not null,
    patientId bigint not null references patients(id),
    treatment text not null,
    createdAt timestamp not null
);