package org.example.repository;

import lombok.Getter;
import org.example.entity.Comment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Getter
public class CommentRepository {
    private List<Comment> comments = new ArrayList<>();

    public void create(Comment user){
        comments.add(user);
    }

    public void delete(long commentId){
        comments = comments.stream()
                .filter(comment -> comment.getId() != commentId)
                .toList();
    }

    public Optional<Comment> read(long commentId){
        Optional<Comment> readComments = comments.stream()
                .filter(id -> id.getId() == commentId)
                .findFirst();
        return readComments;
    }
}
