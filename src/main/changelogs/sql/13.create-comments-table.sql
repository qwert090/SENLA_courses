CREATE TABLE comments
(
    id bigserial NOT NULL, 
    content varchar(200),
    users_id bigint,
    post_id bigint,
    parent_comment_id bigint,
    PRIMARY KEY (id),
    FOREIGN KEY (users_id) references users(id),
    FOREIGN KEY (post_id) references post(id),
    FOREIGN KEY (parent_comment_id) references comments(id)
);
