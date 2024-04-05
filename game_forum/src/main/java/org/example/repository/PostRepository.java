package org.example.repository;

import lombok.Getter;
import org.example.entity.Post;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Getter
public class PostRepository {
    private List<Post> posts = new ArrayList<>();

    public void create(Post post){
        posts.add(post);
    }

    public Optional<Post> read(long postId){
        Optional<Post> readPost = posts.stream()
                .filter(id -> id.getId() == postId)
                .findFirst();
        return readPost;
    }

    public void delete(long postId){
        posts = posts.stream()
                .filter(post -> post.getId() != postId)
                .toList();
    }
}
