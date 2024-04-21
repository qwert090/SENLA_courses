CREATE TABLE credentials_roles
(
    credentials_id bigint,
    roles_id bigint,
    PRIMARY KEY (credentials_id, roles_id),
    FOREIGN KEY (credentials_id) references credentials(id),
    FOREIGN KEY (roles_id) references roles(id)
);
