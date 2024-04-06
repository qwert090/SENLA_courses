package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.CommentDto;
import org.example.entity.Comment;
import org.example.exception.EntityNotFoundException;
import org.example.repository.CommentRepository;
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
        commentRepository.create(comment);

    }

    @Override
    public void deleteById(long id) {
        commentRepository.delete(id);

    }

    @Override
    public CommentDto getById(long id) {
        Comment comment = commentRepository.read(id)
                .orElseThrow(() -> new EntityNotFoundException("no such comment"));
        System.out.println(commentRepository.getComments().stream()
                .filter(comment1 -> comment1.getId() == id)
                .toList()
        );
        return mapper.toDto(CommentDto.class, comment);
    }

    @Override
    public void updateComment(CommentDto commentDto) {
        commentRepository.read(commentDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("no such comment"));
        Comment updateComment = mapper.toEntity(Comment.class, commentDto);
        commentRepository.update(updateComment);
    }
}
