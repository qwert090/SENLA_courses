CREATE TABLE follower
(
    user_id bigint,
    follower_id bigint,
    PRIMARY KEY (user_id, follower_id),
    FOREIGN KEY (user_id) references users(id),
    FOREIGN KEY (follower_id) references users(id)
);
