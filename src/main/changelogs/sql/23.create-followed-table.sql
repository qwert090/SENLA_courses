CREATE TABLE followed
(
    user_id bigint,
    followed_id bigint,
    PRIMARY KEY (user_id, followed_id),
    FOREIGN KEY (user_id) references users(id),
    FOREIGN KEY (followed_id) references users(id)
);
