CREATE TABLE follower
(
    followed_id bigint NOT NULL, 
    follower_id bigint NOT NULL,
    PRIMARY KEY (followed_id, follower_id),
    FOREIGN KEY (followed_id) references "user"(id),
    FOREIGN KEY (follower_id) references "user"(id)
);