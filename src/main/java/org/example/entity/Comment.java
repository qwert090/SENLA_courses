package org.example.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Comment {
    private long id;
    private Comment parentComment;
    private User user;
    private String value;
    private Post post;
}
