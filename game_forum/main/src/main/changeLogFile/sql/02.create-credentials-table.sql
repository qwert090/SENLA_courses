CREATE TABLE credentials
(
    id bigserial NOT NULL, 
    email varchar(60) NOT NULL,
    password varchar(60) NOT NULL,
    PRIMARY KEY (id)
);
