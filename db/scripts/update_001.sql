CREATE TABLE if not exists post
(
    id SERIAL PRIMARY KEY,
    name text,
    description text,
    created text,
    city_id int,
    visible bool
);

CREATE TABLE if not exists candidates
(
    id SERIAL PRIMARY KEY,
    name text,
    photo bytea,
    description text,
    city_id int,
    created text
);
