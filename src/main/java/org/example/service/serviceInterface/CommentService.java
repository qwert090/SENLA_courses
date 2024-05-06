package org.example.service.serviceInterface;

import org.example.dto.CommentDto;

public interface CommentService {

    void createComment(CommentDto commentDto);

    void deleteById(Long id);

    CommentDto getById(Long id);

    void updateComment(CommentDto commentDto);
}
