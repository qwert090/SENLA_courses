CREATE TABLE users
(
    id bigserial NOT NULL, 
    nickname varchar(30),
    description varchar(100),
    avatar varchar(200),
    total_experience int,
    date_creating timestamp with time zone,
    ranks_id bigint,
    credentials_id bigint UNIQUE,
    PRIMARY KEY (id),
    FOREIGN KEY (ranks_id) references ranks(id),
    FOREIGN KEY (credentials_id) references credentials(id)
);
