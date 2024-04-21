CREATE TABLE credentials
(
    id bigserial NOT NULL,
    roles_id bigint,
    email varchar(60),
    password varchar(60),
    PRIMARY KEY (id),
    FOREIGN KEY (roles_id) references roles(id)
);
