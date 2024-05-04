package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.CommentDto;
import org.example.entity.Comment;
import org.example.repository.impl.CommentRepository;
import org.example.service.serviceInterface.CommentService;
import org.example.utils.CustomMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final CustomMapper mapper;

    @Override
    public void createComment(CommentDto commentDto) {
        Comment comment = mapper.toEntity(Comment.class, commentDto);
        commentRepository.save(comment);

    }

    @Override
    public void deleteById(Long id) {
        commentRepository.deleteById(id);

    }

    @Override
    public CommentDto getById(Long id) {
        Comment comment = commentRepository.findById(id);
        return mapper.toDto(CommentDto.class, comment);
    }

    @Override
    public void updateComment(CommentDto commentDto) {
        Comment updateComment = mapper.toEntity(Comment.class, commentDto);
        commentRepository.update(updateComment);
    }
}
