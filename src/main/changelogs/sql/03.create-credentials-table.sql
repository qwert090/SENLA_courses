CREATE TABLE credentials
(
    id bigserial NOT NULL,
    email varchar(60),
    password varchar(60),
    PRIMARY KEY (id)
);
