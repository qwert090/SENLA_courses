package org.example.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Post {
    private long id;
    private String value;
    private User user;
    private List<Category> categories;
    private List<Comment> comments;
}
