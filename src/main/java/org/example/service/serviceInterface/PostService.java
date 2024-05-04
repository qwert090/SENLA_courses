package org.example.service.serviceInterface;

import org.example.dto.PostDto;

public interface PostService {

    void createPost(PostDto postDto);

    void deleteById(Long id);

    PostDto getById(Long id);

    void updatePost(PostDto postDto);
}
