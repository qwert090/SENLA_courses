CREATE TABLE follower
(
    following_id bigint,
    follower_id bigint,
    PRIMARY KEY (following_id, follower_id),
    FOREIGN KEY (following_id) references users(id),
    FOREIGN KEY (follower_id) references users(id)
);
