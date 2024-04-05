CREATE TABLE achivement_request
(
    id bigserial NOT NULL, 
    game_name varchar(30) NOT NULL, 
    achievement_name varchar(30) NOT NULL,
    platform varchar(30) NOT NULL, 
    user_id bigint NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) references "user"(id)
);
