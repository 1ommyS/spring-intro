create table users (
    id bigserial primary key,
    name text not null,
    age int not null,
    email text not null
);