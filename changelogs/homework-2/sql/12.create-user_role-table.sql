CREATE TABLE user_role
(
    credentials_id bigserial NOT NULL, 
    role_id bigserial NOT NULL,
    PRIMARY KEY (credentials_id, role_id),
    FOREIGN KEY (credentials_id) references "user"(id),
    FOREIGN KEY (role_id) references "role"(id)
);