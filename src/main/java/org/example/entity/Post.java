package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "post")
@NoArgsConstructor
@AllArgsConstructor
@NamedEntityGraph(name = "postGraph",
attributeNodes = {@NamedAttributeNode("users"), @NamedAttributeNode("category")})
public class Post extends AbstractEntity{

    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private User users;

    @JoinColumn(name = "category_id")
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Category> category;
}
