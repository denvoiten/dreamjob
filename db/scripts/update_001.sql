CREATE TABLE if not exists post
(
    id SERIAL PRIMARY KEY,
    name text,
    description text,
    created text,
    city_id int,
    visible bool
);