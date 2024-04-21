package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.CommentDto;
import org.example.entity.Comments;
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
        Comments comment = mapper.toEntity(Comments.class, commentDto);
        commentRepository.save(comment);

    }

    @Override
    public void deleteById(long id) {
        commentRepository.deleteById(id);

    }

    @Override
    public CommentDto getById(long id) {
        Comments comment = commentRepository.findById(id);
        return mapper.toDto(CommentDto.class, comment);
    }

    @Override
    public void updateComment(CommentDto commentDto) {
        commentRepository.findById(commentDto.getId());
        Comments updateComment = mapper.toEntity(Comments.class, commentDto);
        commentRepository.update(updateComment);
    }
}
