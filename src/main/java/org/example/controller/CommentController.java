package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.CommentDto;
import org.example.service.serviceInterface.CommentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public void createComment(@RequestBody CommentDto commentDto) {
        commentService.createComment(commentDto);
    }

    @DeleteMapping("/{commentId}")
    public void deleteById(@PathVariable("commentId") Long commentId) {
        commentService.deleteById(commentId);
    }

    @PutMapping
    public void updateComment(@RequestBody CommentDto commentDto) {
        commentService.updateComment(commentDto);
    }

    @GetMapping("/{commentId}")
    public CommentDto getById(@PathVariable("commentId") Long commentId) {
        return commentService.getById(commentId);
    }
}
