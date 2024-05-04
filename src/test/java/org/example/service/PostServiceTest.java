package org.example.service;

import org.example.config.ApplicationConfigTest;
import org.example.dto.PostDto;
import org.example.entity.Post;
import org.example.repository.impl.PostRepository;
import org.example.utils.CustomMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {ApplicationConfigTest.class})
@ExtendWith(MockitoExtension.class)
public class PostServiceTest {
    @Mock
    private PostRepository postRepository;

    @Mock
    private CustomMapper mapper;

    @InjectMocks
    private PostServiceImpl postService;

    @Test
    public void createRankTest() {
        Post post = new Post();
        PostDto postDto = new PostDto();
        when(mapper.toEntity(Post.class, postDto)).thenReturn(post);
        postService.createPost(postDto);
        verify(postRepository, times(1)).save(post);
    }

    @Test
    public void updateRankTest() {
        Post post = new Post();
        PostDto postDto = new PostDto();
        when(mapper.toEntity(Post.class, postDto)).thenReturn(post);
        postService.updatePost(postDto);
        verify(postRepository, times(1)).update(post);
    }

    @Test
    public void getByIdTest() {
        long postId = 1L;
        Post post = new Post();
        PostDto postDto = new PostDto();
        when(mapper.toDto(PostDto.class, post)).thenReturn(postDto);
        when(postRepository.findById(postId)).thenReturn(post);
        PostDto result = postService.getById(postId);
        assertSame(postDto, result);
    }

    @Test
    public void deleteByIdTest() {
        long postId = 1L;
        postService.deleteById(postId);
        verify(postRepository, times(1)).deleteById(postId);
    }
}
