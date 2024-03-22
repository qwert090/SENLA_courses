CREATE TABLE "comment"
(
    id bigserial NOT NULL, 
    value varchar(200) NOT NULL, 
    user_id bigint NOT NULL,
    post_id bigint NOT NULL,
    parrent_comment_id bigint,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) references "user"(id),
    FOREIGN KEY (post_id) references post(id),
    FOREIGN KEY (parrent_comment_id) references "comment"(id)
);