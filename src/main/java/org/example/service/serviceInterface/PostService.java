package org.example.service.serviceInterface;

import org.example.dto.PostDto;

public interface PostService {

    void createPost(PostDto postDto);

    void deleteById(long id);

    PostDto getById(long id);

    void updatePost(PostDto postDto);
}
