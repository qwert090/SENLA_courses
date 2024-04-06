package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.CommentDto;
import org.example.service.serviceInterface.CommentService;
import org.example.utils.JsonMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final JsonMapper json;

    public void createComment(String serializedComment){
        CommentDto commentDto = json.deserialize(serializedComment, CommentDto.class);
        commentService.createComment(commentDto);
    }

    public void deleteById(long commentId){
        commentService.deleteById(commentId);
    }

    public void updateComment(String serializedComment){
        CommentDto commentDto = json.deserialize(serializedComment, CommentDto.class);
        commentService.updateComment(commentDto);
    }

    public CommentDto getById(long commentId){
        return commentService.getById(commentId);
    }
}
