CREATE TABLE game_category
(
    game_id bigint,
    category_id bigint,
    PRIMARY KEY (game_id, category_id),
    FOREIGN KEY (game_id) references game(id),
    FOREIGN KEY (category_id) references category(id)
);
