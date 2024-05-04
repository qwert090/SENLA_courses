CREATE TABLE post
(
    id bigserial NOT NULL, 
    content varchar(500),
    users_id bigint,
    PRIMARY KEY (id),
    FOREIGN KEY (users_id) references users(id)
);
