CREATE TABLE "comment"
(
    id bigserial NOT NULL, 
    value varchar(200) NOT NULL,
    user_id bigint NOT NULL,
    post_id bigint NOT NULL,
    parent_comment_id bigint,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) references users(id),
    FOREIGN KEY (post_id) references post(id),
    FOREIGN KEY (parent_comment_id) references "comment"(id)
);