CREATE TABLE user_role
(
    credentials_id bigint NOT NULL, 
    role_id bigint NOT NULL,
    PRIMARY KEY (credentials_id, role_id),
    FOREIGN KEY (credentials_id) references users(id),
    FOREIGN KEY (role_id) references "role"(id)
);