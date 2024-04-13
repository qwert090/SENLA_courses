CREATE TABLE user_achievements
(
    user_id bigint NOT NULL, 
    achievement_id bigint NOT NULL,
    PRIMARY KEY (user_id, achievement_id),
    FOREIGN KEY (user_id) references users(id),
    FOREIGN KEY (achievement_id) references achievement(id)
);