package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.PostDto;
import org.example.entity.Post;
import org.example.exception.EntityNotFoundException;
import org.example.repository.PostRepository;
import org.example.service.serviceInterface.PostService;
import org.example.utils.CustomMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final CustomMapper mapper;

    @Override
    public void createPost(PostDto postDto) {
        Post post = mapper.toEntity(Post.class, postDto);
        postRepository.create(post);

    }

    @Override
    public void deleteById(long id) {
        postRepository.delete(id);

    }

    @Override
    public PostDto getById(long id) {
        Post post = postRepository.read(id)
                .orElseThrow(() -> new EntityNotFoundException("no such post"));
        System.out.println(postRepository.getPosts().stream()
                .filter(post1 -> post1.getId() == id)
                .toList()
        );
        return mapper.toDto(PostDto.class, post);
    }

    @Override
    public void updatePost(PostDto postDto) {
        postRepository.read(postDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("no such post"));
        Post updatePost = mapper.toEntity(Post.class, postDto);
        postRepository.update(updatePost);
    }
}
