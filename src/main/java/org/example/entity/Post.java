package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "post")
@NamedEntityGraph(name = "postGraph",
attributeNodes = {@NamedAttributeNode("users"), @NamedAttributeNode("category")})
public class Post extends AbstractEntity {

    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Users users;

    @JoinColumn(name = "category_id")
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Category> category;
}
