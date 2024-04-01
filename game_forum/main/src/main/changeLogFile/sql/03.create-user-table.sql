CREATE TABLE "user"
(
    id bigserial NOT NULL, 
    nickname varchar(30) NOT NULL, 
    description varchar(100) NOT NULL,
    avatar varchar(200) NOT NULL,
    total_expirience int NOT NULL,
    rank_id bigint NOT NULL,
    credentials_id bigint NOT NULL UNIQUE,
    PRIMARY KEY (id),
    FOREIGN KEY (rank_id) references "rank"(id),
    FOREIGN KEY (credentials_id) references credentials(id)
);
