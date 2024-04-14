CREATE TABLE users
(
    id bigserial NOT NULL, 
    nickname varchar(30) NOT NULL, 
    description varchar(100) NOT NULL,
    avatar varchar(200) NOT NULL,
    total_experience int,
    rank_id bigint,
    credentials_id bigint UNIQUE,
    PRIMARY KEY (id),
    FOREIGN KEY (rank_id) references "rank"(id),
    FOREIGN KEY (credentials_id) references credentials(id)
);
