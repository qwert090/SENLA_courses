CREATE TABLE achievement
(
    id bigserial NOT NULL, 
    name varchar(30),
    condition varchar(200),
    platform varchar(30),
    experience int,
    type varchar(20),
    game_id bigint,
    PRIMARY KEY (id),
    FOREIGN key (game_id) references game(id)
);
