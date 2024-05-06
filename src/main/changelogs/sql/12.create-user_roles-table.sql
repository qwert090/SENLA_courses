CREATE TABLE users_roles
(
    user_id bigint,
    roles_id bigint,
    PRIMARY KEY (user_id, roles_id),
    FOREIGN KEY (user_id) references users(id),
    FOREIGN KEY (roles_id) references roles(id)
);
