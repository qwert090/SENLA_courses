CREATE TABLE achievement
(
    id bigserial NOT NULL, 
    name varchar(30) NOT NULL, 
    condition varchar(200) NOT NULL,
    platform varchar(30) NOT NULL,
    experience int NOT NULL,
    type varchar(20) NOT NULL,
    game_id bigint NOT NULL,
    PRIMARY KEY (id),
    FOREIGN key (game_id) references game(id)
);