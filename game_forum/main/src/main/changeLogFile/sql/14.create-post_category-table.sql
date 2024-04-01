CREATE TABLE post_category
(
    post_id bigint NOT NULL, 
    category_id bigint NOT NULL,
    PRIMARY KEY (post_id, category_id),
    FOREIGN KEY (post_id) references post(id),
    FOREIGN KEY (category_id) references category(id)
);
