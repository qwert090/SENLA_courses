CREATE TABLE user_achivements
(
    user_id bigserial NOT NULL, 
    achivement_id bigserial NOT NULL,
    PRIMARY KEY (user_id, achivement_id),
    FOREIGN KEY (user_id) references "user"(id),
    FOREIGN KEY (achivement_id) references achivement(id)
);