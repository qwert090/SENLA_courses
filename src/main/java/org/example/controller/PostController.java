package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.PostDto;
import org.example.service.serviceInterface.PostService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public void createPost(@RequestBody PostDto postDto){
        postService.createPost(postDto);
    }

    @DeleteMapping("/{postId}")
    public void deleteById(@PathVariable("postId") Long postId){
        postService.deleteById(postId);
    }

    @PutMapping
    public void updatePost(@RequestBody PostDto postDto){
        postService.updatePost(postDto);
    }

    @GetMapping("/{postId}")
    public PostDto getById(@PathVariable("postId") Long postId){
        return postService.getById(postId);
    }
}
