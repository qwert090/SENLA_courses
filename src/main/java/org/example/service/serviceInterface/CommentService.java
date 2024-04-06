package org.example.service.serviceInterface;

import org.example.dto.CommentDto;

public interface CommentService {

    void createComment(CommentDto commentDto);

    void deleteById(long id);

    CommentDto getById(long id);

    void updateComment(CommentDto commentDto);
}
