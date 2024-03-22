CREATE TABLE game_category
(
    game_id bigserial NOT NULL, 
    category_id bigserial NOT NULL,
    PRIMARY KEY (game_id, category_id),
    FOREIGN KEY (game_id) references game(id),
    FOREIGN KEY (category_id) references category(id)
);