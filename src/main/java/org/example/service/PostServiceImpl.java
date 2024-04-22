package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.dto.PostDto;
import org.example.entity.Post;
import org.example.repository.impl.PostRepository;
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
        postRepository.save(post);

    }

    @Override
    public void deleteById(long id) {
        postRepository.deleteById(id);

    }

    @Override
    public PostDto getById(long id) {
        Post post = postRepository.findById(id);
        return mapper.toDto(PostDto.class, post);
    }

    @Override
    public void updatePost(PostDto postDto) {
        postRepository.findById(postDto.getId());
        Post updatePost = mapper.toEntity(Post.class, postDto);
        postRepository.update(updatePost);
    }
}
