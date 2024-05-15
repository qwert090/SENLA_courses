CREATE TABLE achievement_request
(
    id bigserial NOT NULL, 
    game_name varchar(30),
    achievement_name varchar(30),
    platform varchar(30),
    users_id bigint,
    PRIMARY KEY (id),
    FOREIGN KEY (users_id) references users(id)
);
