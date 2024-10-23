package com.estsoft.springdemoproject.blog.controller;

import com.estsoft.springdemoproject.blog.domain.Comment;
import com.estsoft.springdemoproject.blog.domain.dto.AddCommentRequest;
import com.estsoft.springdemoproject.blog.domain.dto.CommentResponse;
import com.estsoft.springdemoproject.blog.service.BlogService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
@Tag(name = "댓글 수정/삭제/조회용 API", description = "API 설명을 이곳에 작성하면 됩니다")
public class CommentsController {

    private final BlogService blogService;

    public CommentsController(BlogService blogService) {
        this.blogService = blogService;
    }

    @Parameter(name = "id", description = "댓글 ID", example = "45")
    @GetMapping("/comments/{commentId}")
    public ResponseEntity<CommentResponse> findById(@PathVariable Long commentId) {
        Comment comment = blogService.findCommentById(commentId);   // Exception (5xx server error) -> 4xx Status Code

        return ResponseEntity.ok(comment.convertToCommentFindById());
    }

    @PutMapping("/comments/{commentId}")
    public ResponseEntity<CommentResponse> update(@PathVariable Long commentId, @RequestBody AddCommentRequest request) {
        Comment comment = blogService.update(commentId, request);   // Exception (5xx server error) -> 4xx Status Code

        return ResponseEntity.ok(comment.convert());
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity delete(@PathVariable Long commentId) {
        blogService.delete(commentId);   // Exception (5xx server error) -> 4xx Status Code
        return ResponseEntity.ok().build();
    }

}
