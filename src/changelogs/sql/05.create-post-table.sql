CREATE TABLE post
(
    id bigserial NOT NULL, 
    value varchar(500) NOT NULL, 
    user_id bigint NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) references users(id)
);