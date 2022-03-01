alter table patient_cases
add column doctorId bigint references doctors(id);