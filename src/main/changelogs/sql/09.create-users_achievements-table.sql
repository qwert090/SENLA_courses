CREATE TABLE users_achievements
(
    users_id bigint,
    achievement_id bigint,
    PRIMARY KEY (users_id, achievement_id),
    FOREIGN KEY (users_id) references users(id),
    FOREIGN KEY (achievement_id) references achievement(id)
);
