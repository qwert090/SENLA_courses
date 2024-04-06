package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.PostDto;
import org.example.service.serviceInterface.PostService;
import org.example.utils.JsonMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final JsonMapper json;

    public void createPost(String serializedPost){
        PostDto postDto = json.deserialize(serializedPost, PostDto.class);
        postService.createPost(postDto);
    }

    public void deleteById(long postId){
        postService.deleteById(postId);
    }

    public void updatePost(String serializedPost){
        PostDto postDto = json.deserialize(serializedPost, PostDto.class);
        postService.updatePost(postDto);
    }

    public PostDto getById(long postId){
        return postService.getById(postId);
    }
}
