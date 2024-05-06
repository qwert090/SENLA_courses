package org.example.service;

import org.example.config.ApplicationConfigTest;
import org.example.dto.CommentDto;
import org.example.entity.Comment;
import org.example.repository.impl.CommentRepository;
import org.example.utils.CustomMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {ApplicationConfigTest.class})
@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {
    @Mock
    private CommentRepository commentRepository;

    @Mock
    private CustomMapper mapper;

    @InjectMocks
    private CommentServiceImpl commentService;

    @Test
    public void createCommentTest() {
        Comment comment = new Comment();
        CommentDto commentDto = new CommentDto();
        when(mapper.toEntity(Comment.class, commentDto)).thenReturn(comment);
        commentService.createComment(commentDto);
        verify(commentRepository, times(1)).save(comment);
    }

    @Test
    public void updateCommentTest() {
        Comment comment = new Comment();
        CommentDto commentDto = new CommentDto();
        when(mapper.toEntity(Comment.class, commentDto)).thenReturn(comment);
        commentService.updateComment(commentDto);
        verify(commentRepository, times(1)).update(comment);
    }

    @Test
    public void getCommentByIdTest() {
        long commentId = 1L;
        Comment comment = new Comment();
        CommentDto commentDto = new CommentDto();
        when(mapper.toDto(CommentDto.class, comment)).thenReturn(commentDto);
        when(commentRepository.findById(commentId)).thenReturn(Optional.of(comment));
        CommentDto result = commentService.getById(commentId);
        assertSame(commentDto, result);
    }

    @Test
    public void deleteCommentByIdTest() {
        long commentId = 1L;
        commentService.deleteById(commentId);
        verify(commentRepository, times(1)).deleteById(commentId);
    }
}
