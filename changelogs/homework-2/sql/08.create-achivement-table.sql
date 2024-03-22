CREATE TABLE achivement
(
    id bigserial NOT NULL, 
    name varchar(30) NOT NULL, 
    condition varchar(200) NOT NULL,
    platform varchar(30) NOT NULL,
    expirience int NOT NULL,
    type varchar(20) NOT NULL,
    game_id bigserial NOT NULL,
    PRIMARY KEY (id),
    FOREIGN key (game_id) references game(id)
);